M = csvread('matlab_ready_data_file.csv')
tIrys = M(1:150,5:7)
xIrys = M(1:150,1:4)
xIrys = transpose(xIrys)
tIrys = transpose(tIrys)

size(xIrys)
size(tIrys)
setdemorandstream(391418381)
net = patternnet(9);
view(net)
[net,tr] = train(net,xIrys,tIrys);
nntraintool
