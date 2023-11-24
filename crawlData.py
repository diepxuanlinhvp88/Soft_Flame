file = open(r"data\EngtoV.txt",'r', encoding = 'utf8')
wfile = open(r'data\LearnVocab.txt','w',encoding = 'utf8')
s = file.read()
my_list = s.split("\n")
for tmp in my_list:
	data = tmp.split("<token>")
	source = data[1]
	meaning = data[3]
	tmp1 = " "
	tmp2 = "-"
	tmp3 = "."
	if tmp2 in source or tmp1 in source or tmp3 in source:
		continue
	else:
		line = source+"<>" + meaning
		wfile.write(line+"\n")

