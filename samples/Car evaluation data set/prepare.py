orgFile = open('car.data.csv','r')
infile = open('input.csv', 'w')
outfile = open('output.csv', 'w')

def mapBuyingOrMaint(x):
	return {
		'low': 0.99999,
		'med': 0.66666,
		'high': 0.33333,
		'vhigh': 0.0
	}[x]

def mapDoors(x):
	return {
		'5more': 0.99999,
		'4': 0.66666,
		'3': 0.33333,
		'2': 0.0
	}[x]

def mapPersons(x):
	return {
		'more': 1.0,
		'4': 0.5,
		'2': 0.0
	}[x]

def mapLugBoot(x):
	return {
		'big': 1.0,
		'med': 0.5,
		'small': 0.0
	}[x]

def mapSafety(x):
	return {
		'high': 1.0,
		'med': 0.5,
		'low': 0.0
	}[x]
	
def mapClassToNumber(x):
	return {
		'vgood': [0.0,0.0,0.0,1.0],
		'good': [0.0,0.0,1.0,0.0],
		'acc': [0.0,1.0,0.0,0.0],
		'unacc': [1.0,0.0,0.0,0.0]
	}[x]

def handleMapping(x, column):
	x = x.strip('\n')
	if column == 0 or column == 1:
		return str(mapBuyingOrMaint(x))
	elif column == 2:
		return str(mapDoors(x))
	elif column == 3:
		return str(mapPersons(x))
	elif column == 4:
		return str(mapLugBoot(x))
	elif column == 5:
		return str(mapSafety(x))
	return "Error"

def writeToInfile(tokens):
	for i in range(0, len(tokens)-1):
		if i != len(tokens)-2:
			infile.write(handleMapping(tokens[i],i)+',')
		else:
			infile.write(handleMapping(tokens[i],i))
	infile.write('\n')

def stringifyOutputRow(arrRow):
	rBuf = '';
	for number in arrRow:
		rBuf += str(number)+','
	return rBuf[:-1]
		
def writeToOutfile(tokens):
	carClass = tokens[len(tokens)-1]
	carClass = carClass.strip('\n')
	outfile.write(stringifyOutputRow(mapClassToNumber(carClass))+'\n')

lines = orgFile.readlines()

for line in lines:
	tokens = line.split(',')
	writeToInfile(tokens)
	writeToOutfile(tokens)

orgFile.close()
infile.close()
outfile.close()