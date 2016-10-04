var Network = {
	id: "NaN",
	_layers: [],
	synapses: [],
	neuron: [],
}

function setNetwork(xml)
{
	parser = new DOMParser();
	xmlDoc = parser.parseFromString(xmlNetwork , "");
	var nodes = [];
	//draw layers with neurons
	layers = xmlDoc.getElementsByTagName("layer");

	for(var i=0; i < layers.length; i++)
	{
		neurons = layers[i].childNodes;
		for(var j=0; j < neurons.legth; j++)
		{
			
		}
	}
}
