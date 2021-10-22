import requests
import json


json_data_string = {
 "monday": [
 {
 "title": "Nyhederne",
 "state": "begin",
 "time": 21600
 },
 {
 "title": "Nyhederne",
 "state": "end",
 "time": 36000
 },
 {
 "title": "Dybvaaaaad",
 "state": "begin",
 "time": 36000
 },
 {
 "title": "Dybvaaaaad",
 "state": "end",
 "time": 38100
 }
 ],
 "tuesday": [],
 "wednesday": [
 {
 "title": "Nyhederne",
 "state": "begin",
 "time": 21600
 },
 {
 "title": "Nyhederne",
 "state": "end",
 "time": 43200
 },
 {
 "title": "Fodbold",
 "state": "begin",
 "time": 50400
 },
 {
 "title": "Fodbold",
 "state": "end",
 "time": 55800
 },
 {
 "title": "Nyhederne",
 "state": "begin",
 "time": 75600
 },
 {
 "title": "Nyhederne",
 "state": "end",
 "time": 77400
 }
 ],
 "thursday": [
 {
 "title": "ESL",
 "state": "begin",
 "time": 43200
 },
 {
 "title": "ESL",
 "state": "end",
 "time": 46800
 },
 {
 "title": "ESLPro",
 "state": "begin",
 "time": 82800
 }
 ],
 "friday": [
 {
 "title": "ESLPro",
 "state": "end",
 "time": 3600
 }
 ],
 "saturday": [
 {
 "title": "Comedy",
 "state": "begin",
 "time": 52200
 },
 {
 "title": "Comedy",
 "state": "end",
 "time": 59400
 },
 {
 "title": "Nybyggerne",
 "state": "begin",
 "time": 81600
 }
 ],
 "sunday": [
 {
 "title": "Nybyggerne",
 "state": "end",
 "time": 5400
 },
 {
 "title": "Dybvvvvvad",
 "state": "begin",
 "time": 41400
 },
 {
 "title": "Dybvvvvvad",
 "state": "end",
 "time": 43200
 }
 ]
}
r = requests.post(url = "http://localhost:8080/api", data=json.dumps(json_data_string), headers = {'Content-type': 'application/json'})
print(r.content.decode("utf-8"))