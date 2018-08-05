
orgFile = open('iris.data','r')
infile = open('input.csv', 'w')
outfile = open('output.csv', 'w')

def writeToInfile(tokens):
	for i in range(0,len(tokens)-1):
		if i != len(tokens)-2:
			infile.write(tokens[i]+',')
		else:
			infile.write(tokens[i])
	infile.write('\n')

def mapClassToNumber(x):
	x = x.strip('\n')
	return {
		'Iris-setosa': 0.0,
		'Iris-versicolor': 0.5,
		'Iris-virginica': 1.0
	}[x]

def writeToOutfile(tokens):
	irisClass = tokens[len(tokens)-1]
	outfile.write(str(mapClassToNumber(irisClass))+'\n')

lines = orgFile.readlines()

for line in lines:
	tokens = line.split(',')
	writeToInfile(tokens)
	writeToOutfile(tokens)

orgFile.close()
infile.close()
outfile.close()