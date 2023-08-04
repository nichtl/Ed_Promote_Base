package model

import (
	"bufio"
	"encoding/json"
	"fmt"
	"io"
	"io/ioutil"
	"net"
	"net/http"
	"os"
	"strconv"
	"strings"
	"time"
	"util"
)

/*
调用分单系统接口获取订单信息
*/

// 订单信息结构体
type OrderInfo struct {
	OrderType   string
	OrderStatus string
	AgentNo     string
	OrderNo     string
	ServiceNo   string
}

// 返回信息结构体
type ResponseInfo struct {
	MenuChoice int       `json:"menuchoice"`
	Orderinfo  OrderInfo `json:"orderinfo"`
}

type RouteSelector interface {
	GetOrderRouteInfo(ani, dnis string) ([]byte, error)
	GetTargetNumberByAgent(agentid string) string
}
type routeselector struct {
	Client *http.Client
}

//归属地编号 0:南京 1:宿迁
//var agentLocation = 0

const (
	ConvertVDN   = 1400
	ConvertVDNSQ = 1200
	DefaultVDN   = "44101"
)

// 订单系统,CTI系统URL
const IvrAllotTaskUrl = "http://public-api.nj.box-ivr.tuniu.org/ivr/external/task/ivrAllotTask?phoneNo="
const IvrAllotTaskUrlParamCallee = "&inComingCall="

// TEST
// const IvrAllotTaskUrl = "http://10.10.32.152:8881/ivr/external/task/ivrAllotTask?phoneNo="
const AgentStatusUrl = "http://ccmonitor.tuniu.org/inoutapi/GetStatusISAPI.dll?Type=Agent&Action=GetAgentStatusInStringFromAgent2&Para1="
const GroupNoUrl = "http://ccmonitor.tuniu.org/inoutapi/GetStatusISAPI.dll?Type=Agent&Action=GetGroupFromAgent&Para1="

// ccmonitor-sq.tuniu.org 172.24.105.20
const AgentStatusUrlSuQian = "http://ccmonitor-sq.tuniu.org/inoutapi/GetStatusISAPI.dll?Type=Agent&Action=GetAgentStatusInStringFromAgent2&Para1="
const GroupNoUrlSuQian = "http://ccmonitor-sq.tuniu.org/inoutapi/GetStatusISAPI.dll?Type=Agent&Action=GetGroupFromAgent&Para1="

// 四大服务类别
const (
	Hotel   = 1
	Train   = 2
	Plane   = 3 //机票售后
	Finance = 4
	UnKnown = -1
)

// 电话路由指向
const (
	JD               = 5
	Pufa             = 6
	Welcome          = 7
	Serviceing       = 8
	OldSys           = 9
	Serviceed        = 10
	Book             = 11
	InternalPlane    = 12
	ExternalPlane    = 13
	DFPlane          = 14
	BeforeGroup      = 21
	BeforeGroupShort = 21
	BeforeGroupLong  = 21
)

func NewRouteSelector() RouteSelector {
	rs := new(routeselector)
	rs.InitHttpClient()
	return rs
}

// 根据订单类型获取服务类别
func GetServiceType(ordertype string) int {
	var service_type int
	switch ordertype {
	case "6", "16", "64", "69", "72": //酒店国内国际
		service_type = Hotel
		break
	case "17", "30", "33", "34", "38": //用车租车,火车票,火车票分销,火车票
		service_type = Train
		break
	case "31", "32", "37", "61": //国内机票
		//国际机票
		//国内机票
		//新国际机票
		service_type = Plane
		break
	case "21", "22", "24", "43": //金融退税
		//金融汇兑
		//金融存款证明
		//商旅订单
		service_type = Finance
		break
	default: //不存在执行人也非单品
		service_type = UnKnown
		break
	}
	return service_type
}

// 根据主被叫获取订单信息及路由信息
func (rs *routeselector) GetOrderRouteInfo(ani, dnis string) ([]byte, error) {
	orderinfo := IvrAllotTask(rs.Client, ani, dnis)
	//TEST
	//orderinfo := IvrAllotTask_T(rs.Client,ani)
	RSLogger.LogI("orderinfo is [%+v] by ivrallottask return,ani is [%s]", *orderinfo, ani)
	var rspinfo ResponseInfo
	menu_choice := Welcome
	if orderinfo != nil {
		menu_choice = GetServiceType(orderinfo.OrderType)
		if menu_choice != Hotel {
			switch orderinfo.OrderStatus {
			case "1":
				if len(orderinfo.AgentNo) < 5 {
					if menu_choice == -1 {
						menu_choice = Welcome
					}
				} else {
					menu_choice = OldSys
					if orderinfo.OrderType == "7820" {
						menu_choice = BeforeGroup
					}
					if orderinfo.OrderType == "7821" {
						menu_choice = BeforeGroupShort
					}
					if orderinfo.OrderType == "7822" {
						menu_choice = BeforeGroupLong
					}
				}
				break
			case "2":
				if len(orderinfo.AgentNo) < 5 {
					if menu_choice == -1 {
						menu_choice = Serviceing
					}
				} else {
					menu_choice = OldSys
				}
				break
			case "3":
				if len(orderinfo.AgentNo) < 5 {
					if len(orderinfo.ServiceNo) > 1 { //有质检单
						if menu_choice == -1 {
							menu_choice = Serviceed
						}
					} else {
						menu_choice = Welcome
					}
				} else {
					menu_choice = OldSys
				}
				break
			case "4":
				if len(orderinfo.AgentNo) < 5 {
					if len(orderinfo.ServiceNo) > 1 {
						menu_choice = Plane //机票售后
					} else {
						//机票售前国内国际
						if strings.EqualFold(orderinfo.OrderType, "31") || strings.EqualFold(orderinfo.OrderType, "37") {
							menu_choice = InternalPlane
						} else {
							menu_choice = ExternalPlane
						}
					}
				} else {
					menu_choice = OldSys
				}
				break
			case "5":
				if len(orderinfo.AgentNo) < 5 {
					menu_choice = Plane //机票售后
				} else {
					menu_choice = OldSys
				}
				break
			case "6":
				if len(orderinfo.AgentNo) < 5 {
					//机票售前国内国际
					if strings.EqualFold(orderinfo.OrderType, "31") || strings.EqualFold(orderinfo.OrderType, "37") {
						menu_choice = InternalPlane
					} else {
						menu_choice = ExternalPlane
					}
				} else {
					menu_choice = OldSys
				}
				break
			case "7":
				if len(orderinfo.AgentNo) < 5 {
					menu_choice = DFPlane
				} else {
					menu_choice = OldSys
				}
				break
			default:
				menu_choice = Welcome
			}
		}
	}
	if (time.Now().Hour() < 8 || time.Now().Hour() > 22) && (menu_choice == Welcome) {
		menu_choice = Book
	}
	if menu_choice == Welcome {
		if dnis == "68237585" {
			menu_choice = Pufa
		}
	}
	if dnis == "68234000" || dnis == "68234001" {
		menu_choice = JD
	}
	rspinfo.MenuChoice = menu_choice
	rspinfo.Orderinfo = *orderinfo
	bytes, err := json.Marshal(rspinfo)
	return bytes, err
}

func (rs *routeselector) InitHttpClient() {
	rs.Client = &http.Client{
		Transport: &http.Transport{
			Dial: func(netw, addr string) (net.Conn, error) {
				c, err := net.DialTimeout(netw, addr, time.Second*1)
				if err != nil {
					return nil, err
				}
				return c, nil
			},
			MaxIdleConnsPerHost:   10,
			ResponseHeaderTimeout: time.Second * 2,
		},
	}
}

func (rs *routeselector) GetTargetNumberByAgent(agentid string) string {
	status := GetAgentStatusByAgent(rs.Client, agentid)
	targetnumber := DefaultVDN
	if status != "0" && status != "3" { //当状态码为3的时候不做任何处理
		techId := GetTechIdByAgentFromTxt(agentid)
		result := checkSuqianAgent(agentid)
		if result == 0 { //非宿迁
			targetnumber = ConvertTechIdToVDN(techId)
		} else if result == 1 { //宿迁
			targetnumber = ConvertTechIdToVDNSuQian(techId)
		} else {
			RSLogger.LogE("get targetnumber agentid Error, agentid is illegal = [%s]", agentid)
		}
	}
	RSLogger.LogI("get targetnumber success,the agentid is [%s],the targetnumber is [%s]", agentid, targetnumber)
	return targetnumber
}

/*
从分单系统获取订单信息
in:客户来电号码
out:存在返回订单信息,出错返回Null
*/
func IvrAllotTask(client *http.Client, phoneNo string, callee string) *OrderInfo {
	url := IvrAllotTaskUrl + phoneNo + IvrAllotTaskUrlParamCallee + callee
	//RSLogger.LogI("ivrallottask url is [%s]",url)
	req, _ := http.NewRequest("GET", url, nil)
	rsp, err := client.Do(req)
	if err == nil {
		if rsp.StatusCode == http.StatusOK {
			body, err := ioutil.ReadAll(rsp.Body)
			defer rsp.Body.Close()
			if err == nil {
				array := strings.Split(string(body), ",")
				RSLogger.LogI("ivrallottask success,ani is [%s],body is [%s]", phoneNo, array)
				return &OrderInfo{AgentNo: array[0], OrderStatus: array[1], OrderType: array[2], OrderNo: array[3], ServiceNo: array[4]}
			}
			RSLogger.LogE("ivrallottask err,err is [%s]", err.Error())
			util.Send(fmt.Sprintf("ivrallottask err,err is [%s]", err.Error()))
		} else {
			RSLogger.LogE("ivrallottask err,http response code err is [%s]", rsp.StatusCode)
			util.Send(fmt.Sprintf("ivrallottask err,http response code err is [%s]", rsp.StatusCode))
		}
	} else {
		RSLogger.LogE("ivrallottask err,client.do err is [%s]", err.Error())
		util.Send(fmt.Sprintf("ivrallottask err,client.do err is [%s]", err.Error()))
	}
	return &OrderInfo{"0", "0", "0", "0", "0"}
}

func IvrAllotTask_T(client *http.Client, phoneNo string) *OrderInfo {
	url := IvrAllotTaskUrl + phoneNo
	req, _ := http.NewRequest("GET", url, nil)
	rsp, err := client.Do(req)
	if err == nil {
		if rsp.StatusCode == http.StatusOK {
			body, err := ioutil.ReadAll(rsp.Body)
			defer rsp.Body.Close()
			if err == nil {
				array := strings.Split(string(body), ",")
				RSLogger.LogI("ivrallottask success,ani is [%s],body is [%s]", phoneNo, array)
				array[0] = "81888"
				array[1] = "3"
				array[2] = "37"
				array[3] = "1027206002"
				array[4] = "0"
				return &OrderInfo{AgentNo: array[0], OrderStatus: array[1], OrderType: array[2], OrderNo: array[3], ServiceNo: array[4]}
			}
			RSLogger.LogE("ivrallottask err,err is [%s]", err.Error())
		} else {
			RSLogger.LogE("ivrallottask err,http response code err is [%s]", rsp.StatusCode)
		}
	} else {
		RSLogger.LogE("ivrallottask err,client.do err is [%s]", err.Error())
	}
	return &OrderInfo{"0", "0", "0", "0", "0"}
	//"OrderType":"37","OrderStatus":"3","AgentNo":"86693","OrderNo":"1027206002","ServiceNo":"0"
}

/*
从CTI系统获取坐席状态
*/
func GetAgentStatusByAgent(client *http.Client, agentid string) string {
	RSLogger.LogI("get agentstatus agentid = [%s]", agentid)

	url := ""
	result := checkSuqianAgent(agentid)
	if result == 1 {
		url = AgentStatusUrlSuQian + agentid
	} else if result == 0 {
		url = AgentStatusUrl + agentid
	} else {
		RSLogger.LogE("get agentstatus agentid Error, agentid is illegal = [%s]", agentid)
		return "0"
	}
	RSLogger.LogI("get agentstatus uri = [%s]", url)
	req, _ := http.NewRequest("GET", url, nil)
	rsp, err := client.Do(req)
	if err == nil {
		if rsp.StatusCode == http.StatusOK {
			body, err := ioutil.ReadAll(rsp.Body)
			defer rsp.Body.Close()
			if err == nil {
				RSLogger.LogI("get agentstatus success,agentid is [%s],body is [%s]", agentid, body)
				return string(body)
			}
			RSLogger.LogE("get agentstatus err,agentid is [%s],err is [%s]", agentid, err.Error())
		}
	}
	return "0"
}

func GetSkillNoByAgent(client *http.Client, agentid string) string {
	RSLogger.LogI("get skillno agentid = [%s]", agentid)
	agentLocation := CheckAgentLocationByTxt(agentid)
	url := GroupNoUrl + agentid
	if agentLocation == 1 {
		url = GroupNoUrlSuQian + agentid
	}
	RSLogger.LogI("get skillno uri = [%s]", url)
	req, _ := http.NewRequest("GET", url, nil)
	rsp, err := client.Do(req)
	if err == nil {
		if rsp.StatusCode == http.StatusOK {
			body, err := ioutil.ReadAll(rsp.Body)
			defer rsp.Body.Close()
			if err == nil {
				RSLogger.LogI("get skillNo success,agentid is [%s],body is [%s]", agentid, body)
				return string(body)
			}
			RSLogger.LogI("get skillNo err,agentid is [%s],err is [%s]", agentid, err.Error())
		}
	}
	return ""
}

func ConvertTechIdToVDN(skillNo string) string {
	vdnId := "44101"
	if len(skillNo) > 0 {
		switch skillNo {
		case "1":
			vdnId = "44101"
			break
		case "2":
			vdnId = "44282"
			break
		default:
			vdnId = "442" + skillNo
			break
		}
	}
	return vdnId
}

func ConvertTechIdToVDNSuQian(skillNo string) string {
	vdnId := "44101"
	if len(skillNo) > 0 {
		switch skillNo {
		case "1":
			vdnId = "44101"
			break
		case "2":
			vdnId = "44282"
			break
		default:
			vdnId = "443" + skillNo
			break
		}
	}
	return vdnId
}

// 根据坐席号码获取坐席techId
func GetTechIdByAgentFromTxt(agentid string) string {
	fileName := ""
	result := checkSuqianAgent(agentid)
	if result == 1 {
		fileName = "./suqian_agent-loginID.txt"
	} else if result == 0 {
		fileName = "./report_list_agent-loginID.txt"
	} else {
		RSLogger.LogE("GetTechIdByAgentFromTxt error,agentid is illegal = [%s]", agentid)
		return ""
	}
	file, err := os.Open(fileName)
	defer file.Close()
	if err == nil {
		reader := bufio.NewReader(file)
		for {
			line, _, err := reader.ReadLine()
			if err == io.EOF {
				break
			}
			if err == nil {
				array := strings.Split(string(line), ",")
				if array[0] == "\""+agentid+"\"" {
					RSLogger.LogI("get techId success,the agentid is [%s],the techId is %s", agentid, array[8])
					if result == 0 {
						return array[6][1 : len(array[6])-1]
					} else if result == 1 {
						return array[8][1 : len(array[8])-1]
					}
				}
			} else {
				RSLogger.LogE("get techId err,err is %s", err.Error())
			}

		}
	}
	return ""
}

// 根据本地文本识别坐席归属地
func CheckAgentLocationByTxt(agentid string) int {
	file, err := os.Open("./suqian_agent-loginID.txt")
	agentLocation := 0
	defer file.Close()
	if err == nil {
		reader := bufio.NewReader(file)
		for {
			line, _, err := reader.ReadLine()
			if err == io.EOF {
				break
			}
			if err == nil {
				array := strings.Split(string(line), ",")
				if array[0] == "\""+agentid+"\"" {
					RSLogger.LogI("bingo! find agentid from [suqian] txt %s", array[0])
					agentLocation = 1
					return agentLocation
				} else {
					agentLocation = 0
				}
			} else {
				RSLogger.LogE("get agentId err,err is %s", err.Error())
			}

		}
	}
	return agentLocation
}

// 根据号码开头两位判断坐席归属地
func CheckAgentLocationByHead(agentid string) int {
	agentLocation := 0
	if strings.IndexAny(agentid, "80") == 0 {
		agentLocation = 1
	} else if strings.IndexAny(agentid, "81") == 0 {
		agentLocation = 1
	}
	return agentLocation
}

/*1是，0非，-1其他*/
func checkSuqianAgent(agentid string) int {
	agentNumber, error := strconv.Atoi(agentid)
	if error != nil {
		RSLogger.LogE("get agentstatus agentid Error, agentid is not a number = [%s]", agentid)
		return -1
	}
	if agentNumber >= 85000 && agentNumber <= 89998 { //非宿迁
		return 0
	} else if agentNumber >= 80000 && agentNumber <= 81999 { //宿迁
		return 1
	} else {
		return -1
	}
}

func sendMail(info string) {
	user := "***@tuniu.com"
	password := "***"
	host := "***.tuniu.com:25"
	to := "***@tuniu.com"

	err := util.SendToMail(user, password, host, to, "分单系统返回异常", info, "html")
	if err != nil {
		RSLogger.LogE("sendMail err,err is %s", err.Error())
	}
}
