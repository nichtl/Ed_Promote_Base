package model

import (
	_ "bufio"
	"fmt"
	"log"
	"os"
	"runtime"
	"strings"
	"time"
)

var RSLogger Logger

type Logger interface {
	Log(level string, fmt string, v ...interface{})
	LogI(fmt string, v ...interface{})
	LogE(fmt string, v ...interface{})
	LogW(fmt string, v ...interface{})
}

type logger struct {
	tag     string
	level   int
	path    string
	console bool
	mux     chan int
	file    *os.File
	today   string
}

const (
	INFO  = 1
	WARN  = 2
	ERROR = 3
)

func LevelStr2N(level string) int {
	rv := -1
	switch {
	case strings.EqualFold(level, "INFO"):
		rv = INFO
		break
	case strings.EqualFold(level, "ERROR"):
		rv = ERROR
		break
	case strings.EqualFold(level, "WARN"):
		rv = WARN
		break
	}
	return rv
}

func NewLogger(tag string, path string, level string, console bool) Logger {
	ret := new(logger)
	ret.tag = tag
	ret.path = path
	ret.level = LevelStr2N(level)
	if ret.level == -1 {
		fmt.Printf("unknown level(%s)\n", level)
		return nil
	}
	ret.console = console
	ret.file = nil
	ret.mux = make(chan int, 1)
	ret.mux <- 1
	return ret
}

func FileExist(file string) bool {
	_, err := os.Stat(file)
	if err == nil {
		return true
	}
	return false
}

func (this logger) Log(level string, f string, v ...interface{}) {
	var file *os.File
	var err error
	today := time.Now().Format("2006-01-02")
	logpath := this.path + "/" + time.Now().Format("2006-01-02") + "/" + "ivr.log"

	curLevel := LevelStr2N(level)
	if curLevel < this.level {
		return
	}

	<-this.mux
	if !strings.EqualFold(today, this.today) {
		this.file.Close()
		this.today = today
		this.file = file
		if !FileExist(logpath) {
			err = os.MkdirAll(this.path+"/"+this.today, 1)
			if err != nil {
				log.Println("mkdirall err is %s", err.Error())
			}
		}
		file, err = os.OpenFile(logpath, os.O_APPEND|os.O_CREATE|os.O_RDWR, 0666)
		if err == nil {
			this.file = file
		} else {
			log.Println("openfile err is %s", err.Error())
		}
	}

	head := fmt.Sprintf("[%s] [%s] [%s]", this.tag, time.Now().Format("2006-01-02 15:04:05"), level)
	msg := fmt.Sprintf(f, v...)
	var logPos string
	if strings.ToLower(level) == "error" || strings.ToLower(level) == "warn" {
		pc, file, line, _ := runtime.Caller(3)
		funcInfo := runtime.FuncForPC(pc)
		logPos = fmt.Sprintf(" %s@%s:%d ", funcInfo.Name(), file, line)
	}
	if this.file != nil {
		_, err = this.file.WriteString(head + msg + logPos + "\n")
		if err != nil {
			log.Println(err.Error())
		}
	}
	this.mux <- 1

	if this.console {
		log.Println(head + msg + logPos)
	}
}

func (this logger) LogI(fmt string, v ...interface{}) {
	this.Log("INFO", fmt, v...)
}

func (this logger) LogE(fmt string, v ...interface{}) {
	this.Log("ERROR", fmt, v...)
}

func (this logger) LogW(fmt string, v ...interface{}) {
	this.Log("WARN", fmt, v...)
}
