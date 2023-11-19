import sqlite3
conn = sqlite3.connect(r"D:\UET\Soft_Flame\Dictionary\data\dict_hh.db")
cursor = conn.cursor()
cnt = 0
file = open(r"D:\UET\Soft_Flame\Dictionary\data\EngtoV.txt",'w',encoding = "utf8")
for i in range(1,108855):
	tmp = ""
	try:
		cursor.execute("SELECT * FROM av where id = '{}';".format(i))
		data = cursor.fetchall()
		tmp += str(data[0][0])+"<token>"+str(data[0][1])+"<token>"+str(data[0][2])+"<token>"+str(data[0][3])+"<token>"+str(data[0][4]) 
		file.write(tmp+"\n")
	except:
		cnt+=1
		print("error id{}".format(i))
