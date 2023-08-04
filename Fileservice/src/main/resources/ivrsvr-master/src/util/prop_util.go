package util

import (
	"bufio"
	"io"
	"os"
	"strings"
)

var Prop *PropUtil = NewPropUtil()

type PropUtil struct {
	m map[string]string
}

func NewPropUtil() *PropUtil {
	prop := &PropUtil{
		make(map[string]string),
	}
	prop.read("application.properties")
	return prop
}

func (p *PropUtil) Get(key string) string {
	if key == "" {
		return ""
	}
	return p.m[key]
}

func (p *PropUtil) read(propFileName string) error {
	file, err := os.OpenFile(propFileName, os.O_RDONLY, 0660)
	if err != nil {
		return err
	}
	defer file.Close()
	reader := bufio.NewReader(file)
	p.m = make(map[string]string)
	for {
		line, err := reader.ReadString('\n')
		props := strings.SplitN(line, "=", 2)
		if len(props) >= 2 {
			p.m[strings.TrimSpace(props[0])] = strings.TrimSpace(props[1])
		}
		if err != nil || io.EOF == err {
			break
		}
	}
	return nil
}
