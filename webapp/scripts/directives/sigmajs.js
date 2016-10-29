(function() {
	'use strict';

	app.directive('sigmajs', function() {
		sigma.classes.graph.addMethod('neighbors', function(nodeId) {
			var k,
				neighbors = {},
				index = this.allNeighborsIndex[nodeId] || {};

			for (k in index)
			  neighbors[k] = this.nodesIndex[k];

			return neighbors;
		});
		//over-engineered random id, so that multiple instances can be put on a single page
		var divId = 'sigmjs-dir-container-'+Math.floor((Math.random() * 999999999999))+'-'+Math.floor((Math.random() * 999999999999))+'-'+Math.floor((Math.random() * 999999999999));
		return {
			restrict: 'E',
			template: '<div id="'+divId+'" class="canvas"></div>',
			scope: {
				//@ reads the attribute value, = provides two-way binding, & works with functions
				graph: '=',
				width: '@',
				height: '@',
				releativeSizeNode: '='
			},
			link: function (scope, element, attrs) {
				// Let's first initialize sigma:
				var s = new sigma({
					container: divId,
					settings: {
						defaultNodeColor: '#ec5148',
						labelThreshold: 4
					}
				});

				scope.$watch('graph', function(newVal,oldVal) {
					s.graph.clear();
					s.graph.read(scope.graph);
					s.refresh();
					if(scope.releativeSizeNode) {
						//this feature needs the plugin to be added
						sigma.plugins.relativeSize(s, 2);
					}
				});
	
				scope.$watch('width', function(newVal,oldVal) {
					console.log("graph width: "+scope.width);
					element.children().css("width",scope.width);
					s.refresh();
					window.dispatchEvent(new Event('resize')); //hack so that it will be shown instantly
				});
				scope.$watch('height', function(newVal,oldVal) {
					console.log("graph height: "+scope.height);
					element.children().css("height",scope.height);
					s.refresh();
					window.dispatchEvent(new Event('resize'));//hack so that it will be shown instantly
				});
	
				element.on('$destroy', function() {
					s.graph.clear();
				});
				sigma.classes.graph.addMethod('neighbors', function(nodeId) {
					var k,
						neighbors = {},
						index = this.allNeighborsIndex[nodeId] || {};

					for (k in index)
					  neighbors[k] = this.nodesIndex[k];

					return neighbors;
				});

				s.bind('clickNode', function(e) {
					var nodeId = e.data.node.id, toKeep = s.graph.neighbors(nodeId);
					toKeep[nodeId] = e.data.node;

					s.graph.nodes().forEach(function(n) {
						if (toKeep[n.id])
							n.color = '#27ae60';
						else
							n.color = '#aaa';
					});

					s.graph.edges().forEach(function(e) {
						if (toKeep[e.source] && toKeep[e.target])
							e.color = '#27ae60';
						else
							e.color = '#aaa';
					});
					s.refresh();
				});
				
				// When the stage is clicked, we just color each
				// node and edge with its original color.
				s.bind('clickStage', function(e) {
					s.graph.nodes().forEach(function(n) {
						n.color = n.originalColor;
					});
					s.graph.edges().forEach(function(e) {
						e.color = e.originalColor;
					});
					// Same as in the previous event:
					s.refresh();
				  });
			}
		};
	});
})();