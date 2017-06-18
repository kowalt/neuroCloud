import struct

TRAIN_IMAGE_FILE_IN = 'train-images.idx3-ubyte'
TRAIN_IMAGE_FILE_IN_CORRECT_MAGIC_NUMBER = 2051;
TRAIN_LABEL_FILE_IN = 'train-labels.idx1-ubyte'
TRAIN_LABEL_FILE_IN_CORRECT_MAGIC_NUMBER = 2049;
LEARNING_OUT_FILE = 'learning.csv'
TRAINING_OUT_FILE = 'training.csv'

class Pixel:
	value = -1
	valueNormalized = 0.0

class Image:
	pixels = []
	label = -1
	labelNormalized = -1

def minMaxNormalization(x):
	return x/255

print('Starting...')

f_train_images = open(TRAIN_IMAGE_FILE_IN,'rb');
magic_number = struct.unpack('>i', f_train_images.read(4))[0]


if(TRAIN_IMAGE_FILE_IN_CORRECT_MAGIC_NUMBER == magic_number):
	print('Train image file magic number is correct');
else:
	print('Test image file magic number is incorrect: '+str(magic_number))

num_of_images = struct.unpack('>i', f_train_images.read(4))[0]
num_of_rows = struct.unpack('>i', f_train_images.read(4))[0]
num_of_columns = struct.unpack('>i', f_train_images.read(4))[0]

f_train_labels = open(TRAIN_LABEL_FILE_IN,'rb')

magic_number = struct.unpack('>i', f_train_labels.read(4))[0]

if(TRAIN_LABEL_FILE_IN_CORRECT_MAGIC_NUMBER == magic_number):
	print('Train label file magic number is correct');
else:
	print('Test label file magic number is incorrect: '+str(magic_number))

if(num_of_images != struct.unpack('>i', f_train_labels.read(4))[0]):
	print('Declared number of items aren\'t equal')

f_out_learning = open(LEARNING_OUT_FILE, 'w')
f_out_training = open(TRAINING_OUT_FILE, 'w')

for i in range(0, num_of_images):
	image = Image()
	image.label = struct.unpack('>B', f_train_labels.read(1))[0]
	image.labelNormalized = minMaxNormalization(image.label)
	f_out_training.write(str(image.labelNormalized))
	f_out_training.write('\n')
	for j in range(0, num_of_rows):
		for k in range(0, num_of_columns):
			pixel = Pixel()
			pixel.value = struct.unpack('>B', f_train_images.read(1))[0]
			pixel.valueNormalized = minMaxNormalization(pixel.value)
			f_out_learning.write(str(pixel.valueNormalized))
			if k != num_of_columns -1:
				f_out_learning.write(',')
	f_out_learning.write('\n')

f_train_images.close()
f_train_labels.close()
f_out_learning.close()
f_out_training.close()