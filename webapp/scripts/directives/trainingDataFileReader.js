app.directive('trainingDataFileReader', function() {
  return {
	restrict: 'A',
    link: function(scope, element, attrs) {
      element.on('change', function(changeEvent) {
        var files = changeEvent.target.files;
        if (files.length) {
          var r = new FileReader();
          r.onload = function(e) {
              var contents = e.target.result;
              scope.$apply(function () {
				function digestCSV(rawContent)
				{
					var retArr = [];
					var arrayOfLines = rawContent.match(/[^\r\n]+/g);

					for(var i=0; i<arrayOfLines.length; i++)
					{
						retArr[i] = arrayOfLines[i].split(',');
						retArr[i] = parseFloat(retArr[i]);
					}

					return retArr;
				}
				if(attrs.fileType === 'learning')
					scope.trainingProps.learningSet = digestCSV(contents);
				if(attrs.fileType === 'training')
					scope.trainingProps.trainingSet = digestCSV(contents);
              });
          };

          r.readAsText(files[0]);
        }
      });
    }
  };
});
