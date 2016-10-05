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
function transformNodes(xmlNetwork)
{
	parser = new DOMParser();
	xmlDoc = parser.parseFromString(xmlNetwork , "application/xml");
	var nodes = [];

	var _layers = xmlDoc.getElementsByTagName("layer");

	for(var i=0; i < _layers.length; i++)
	{
		neurons = _layers[i].childNodes;
		for(var j=0; j < neurons.legth; j++)
		{
			var node;
			node.x = 1/_layers.length*layerIndex; // height instead of 1 before normalisation
			node.y = 1/neurons.length*neuronIndex; // width ----||----
			node.id = neurons[j].getAttribute('id');
			nodes.push(node);
		}
	}

	return nodes;
}

/*
	Transforms synapses to edges
*/
function transformEdges(xmlNetwork)
{
	parser = new DOMParser();
	xmlDoc = parser.parseFromString(xmlNetwork, "application/xml");
	
	var synapses = xmlDoc.getElementsByTagName("synapse");
	var edges = [];
		
	for(var i=0; i < synapses.length; i++ )
	{
		var edge;
		edge.source = xmlDoc.getAttribute('from');
		edge.target = xmlDoc.getAttribute('to');
		edge.id = synapses[i].getAttribute('id');
		edges.push(edge);
	}
	
	return edges;
}