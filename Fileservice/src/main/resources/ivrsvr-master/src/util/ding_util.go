package util

import (
	"bytes"
	b64 "encoding/base64"
	"encoding/json"
	"net/http"
	"strconv"
	"strings"
)

type MsgRequest struct {
	StepId  int    `json:"stepId"`
	MsgType int    `json:"msgType"`
	Content string `json:"content"`
	UIDS    []int  `json:"uids"`
}

func Send(message string) error {
	uidsInProp := Prop.Get("uids")
	if len(uidsInProp) == 0 {
		return nil
	}
	uidItems := strings.Split(uidsInProp, ",")
	if uidItems == nil || len(uidItems) == 0 {
		return nil
	}
	uids := []int{}
	for _, uid := range uidItems {
		uidInt, err := strconv.Atoi(uid)
		if err == nil {
			uids = append(uids, uidInt)
		} else {
			return nil
		}
	}
	return SendToUser(uids, message)
}

func SendToUser(uids []int, message string) error {
	_, err := reqeustDingApi(1, uids, message)
	return err
}

func reqeustDingApi(msgType int, uids []int, message string) (interface{}, error) {
	var msgRequest MsgRequest
	msgRequest.StepId = 1001
	msgRequest.MsgType = msgType
	msgRequest.Content = message
	msgRequest.UIDS = uids
	//序列化
	jsonStr, err := json.Marshal(msgRequest)
	if err != nil {
		return nil, err
	}
	//base64序列化
	b64Str := b64.StdEncoding.EncodeToString([]byte(jsonStr))
	//向底层接口发送请求
	ddturl := ""

	req, err := http.NewRequest("POST", ddturl, bytes.NewBuffer([]byte(b64Str)))
	client := &http.Client{}
	resp, err := client.Do(req)

	if err != nil {
		return nil, err
	}

	defer resp.Body.Close()

	//body, _ := ioutil.ReadAll(resp.Body)
	//json反序列化
	//decodeBody, _ := b64.StdEncoding.DecodeString(string(body))
	//jsonBody, err := simplejson.NewJson(decodeBody)
	return nil, err

}
