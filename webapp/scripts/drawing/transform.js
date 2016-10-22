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
		for(var j=0; j < Math.floor(neurons.length/2); j++)
		{
			var node = {};
			node.x = 1/_layers.length*i; // height instead of 1 before normalisation
			node.y = 1/neurons.length*j; // width ----||----
			node.id = neurons[j*2+1].getAttribute('id');
			nodes.push(node);
		}
	}

	return nodes;
}

/*
	Transforms synapses to edges, input and output
*/
function transformEdges(xmlNetwork)
{
	parser = new DOMParser();
	xmlDoc = parser.parseFromString(xmlNetwork, "application/xml");
	
	var synapses = xmlDoc.getElementsByTagName("synapse");
	var edges = [];
	var inputVec = [];
	var outputVec = [];

	for(var i=0; i < synapses.length; i++ )
	{
		var edge = {};

		source = synapses[i].getAttribute('from');
		target = synapses[i].getAttribute('to');

		if(source == "0" || target == "0")
		{
			if(source == "0")
				inputVec.push(synapses[i].getAttribute("value"));
			if(target == "0")
				outputVec.push(synapses[i].getAttribute("value"));
			continue;
		}

		edge.source = source;
		edge.target = target;
		edge.id = synapses[i].getAttribute('id');
		edges.push(edge);
	}

	return [edges, inputVec, outputVec];
}
