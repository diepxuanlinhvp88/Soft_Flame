q = open('rerange.txt','r')
a = open('KetRerange.txt','r',encoding = 'utf8')

question = q.read().split("\n")
answer = a.read().split("\n")
tmp = ""
for i in range(len(question)):
	tmp += question[i]+"<tokens>"+answer[i]+"\n"
des = open("FillBlankCom1.txt",'a',encoding = 'utf8')
des.write(tmp)