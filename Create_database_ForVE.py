import sqlite3

conn = sqlite3.connect(r"D:\java_code\Soft_Flame\Dictionary\data\ViEn.db")

cursor = conn.cursor()

file = open(r"D:\java_code\Soft_Flame\Dictionary\data\vietanh.txt",'r',encoding = 'utf8')
data = file.read()
data = data.split("@")
data= data[4:]
for i in range(len(data)):
	tmp = data[i].split("\n")
	for j in range(len(tmp)):
		if len(tmp[j])>0 and tmp[j][0] == "=":
			tmpList = tmp[j].split("+")
			tmpString = ""
			if len(tmpList) == 2:
				tmpString = tmpList[1]+tmpList[0]
			tmp[j] = tmpString


