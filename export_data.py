import sqlite3
def connect():
	conn = sqlite3.connect(r"data\dict_hh.db")
	cursor = conn.cursor()
	file = open(r"data\EngtoV.txt",'a',encoding = "utf8")
	info = open(r"data\infoEditWord.txt",'r')
	data = info.read()
	index = data.split("\n")
	index = index[:-1]
	for i in index:
		tmp = ""
		cursor.execute("SELECT * FROM av where id = '{}';".format(i))
		data = cursor.fetchall()
		tmp += str(data[0][0])+"<token>"+str(data[0][1])+"<token>"+str(data[0][2])+"<token>"+str(data[0][3])+"<token>"+str(data[0][4]) 
		file.write(tmp+"\n")
if __name__ == "__main__":
	connect()
