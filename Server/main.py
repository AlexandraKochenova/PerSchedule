# -*- coding: utf-8 -*-
"""
Created on Wed Sep 18 19:34:32 2019

@author: 123
"""

from flask import Flask
import json
app=Flask(__name__)

id = 0

@app.route("/")
def hello():
    return "Hello World!"

@app.route("/<username>", methods=['GET'])
def index(username):
    print(username + " GET");
    global id
    id+=1
    response = {"id" : str(id), "username" : username}
    response = json.dumps(response)
    #response_json = json.loads(response)
    return response


if __name__ == "__main__":
    app.run(host="192.168.43.185", port=3149)
    #app.run(host="127.0.0.1", port=3149)

