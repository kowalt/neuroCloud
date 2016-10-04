/*
* 
returns 
node.id
node.label
node.x
node.y
node.size
node.color
*/
function transform(xmlNetwork)
{
	parser = new DOMParser();
	xmlDoc = parser.parseFromString(xmlNetwork , "");
	var nodes = [];
	//draw layers with neurons
	_layers = xmlDoc.getElementsByTagName("layer");

	for(var i=0; i < _layers.length; i++)
	{
		neurons = _layers[i].childNodes;
		for(var j=0; j < neurons.legth; j++)
		{
			var node;
			node.x = 1/_layers.length*layerIndex; // height instead of 1 before normalisation
			node.y = 1/neurons.length*neuronIndex; // width ----||----
			nodes.push(node);
		}
	}

	return nodes;
}