package main

import (
	_ "bytes"
	"model"
	"mux"
	"net/http"
	_ "strings"
	"util"
)

// 根据来电号码获取订单信息
func getOrderInfo(rsp http.ResponseWriter, req *http.Request) {
	vars := mux.Vars(req)
	ani := vars["ani"]
	dnis := vars["dnis"]
	model.RSLogger.LogI("recv new request for orderinfo,the ani is [%s],the dnis is [%s]", ani, dnis)
	bodys, err := rs.GetOrderRouteInfo(ani, dnis)
	rsp.Header().Set("content-type", "application/json")
	rsp.WriteHeader(http.StatusOK)
	if err == nil {
		model.RSLogger.LogI("get_order_routeinfo success,the orderinfo and menuchoice is [%s]", bodys)
		rsp.Write(bodys)
	} else {
		model.RSLogger.LogE("get_order_routeinfo occurs err,err is [%s]", err.Error())
		rsp.Write([]byte(""))
	}
}

// 根据坐席工号获取路由目的地
func getTargetNumber(rsp http.ResponseWriter, req *http.Request) {
	vars := mux.Vars(req)
	agentId := vars["agentid"]
	model.RSLogger.LogI("recv new request for targetnumber,the agentid is [%s]", agentId)
	bodys := rs.GetTargetNumberByAgent(agentId)
	rsp.Header().Set("content-type", "text/plain")
	rsp.WriteHeader(http.StatusOK)
	rsp.Write([]byte(bodys))
}

/*
http://ip/RouteService/OrderInfo/dnis:[0-9]/ani:[0-9]
http://ip/RouteService/TargetAgentMap/agentid:[0-9]
*/

/*
v0.1.0
1. receive AVP custom mobile number -> getOrderInfo -> return AVP
2. AVP parseOrderInfo -> agentid
3. receive agentid -> getAgentStatus
4. check agent location [loc] 0:nanjing 1:suqian
5. get agent status from CTI url decide on [loc]
6. if status logout -> getVdn
7. get vdn decide on [loc]
8. return AVP the called vdn?
*/
var rs model.RouteSelector

func main() {
	r := mux.NewRouter()
	s := r.PathPrefix("/RouteService").Subrouter()
	s.HandleFunc("/OrderInfo/{dnis:[0-9]+}/{ani:[0-9]+}", getOrderInfo).Methods("GET")
	s.HandleFunc("/TargetAgentMap/{agentid:[0-9]+}", getTargetNumber).Methods("GET")

	path := "./log"
	if 0 != len(util.Prop.Get("log_path")) {
		path = util.Prop.Get("log_path")
	}
	model.RSLogger = model.NewLogger("IVR", path, "INFO", true)
	rs = model.NewRouteSelector()
	v := "0.1.0"
	model.RSLogger.LogI("ivrsvr version is [%s]", v)
	model.RSLogger.LogI("server start and listening on port 80 ...")
	http.ListenAndServe(":80", r)

}
