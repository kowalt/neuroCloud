/*
* 
returns node_a.id
node_a.label
node_a.x
node_a.y
node_a.size
node_a.color 
*/
function transform(xmlNetwork, canvas_width, canvas_length)
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