app.directive('uploadInputFileReader', function() {
  return {
	restrict: 'A',
    link: function(scope, element, attrs) {
      element.on('change', function(changeEvent) {
        var files = changeEvent.target.files;
        if (files.length) {
          var r = new FileReader();
          r.onload = function(e) {
              var contents = e.target.result;
			  var inputVecBuff = contents.split(',');
              scope.$apply(function () {
				scope.inoutContainer.input_vector = inputVecBuff;
              });
          };
          r.readAsText(files[0]);
        }
      });
    }
  };
});
