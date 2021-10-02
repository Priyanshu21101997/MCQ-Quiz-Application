var myControllers = angular.module('quizApp');

myControllers.controller('quesController',quesController);

function quesController($http,$scope){
	var vm = this;
	vm.selectAnswer = selectAnswer;
	vm.evaluateQuiz = evaluateQuiz
	vm.finaliseAnswers = finaliseAnswers;
	vm.error = false;
	vm.finalise = false;
	vm.hideQuiz = false;
	vm.results = false;
	$scope.data = [];
	vm.finalScore = 0;
	vm.correctArray = [];
	vm.selectedOptionArray = [];
	vm.calculatePerc = calculatePerc;
	vm.reset = reset;
	var optionSelected={};
	

	

	
	_refreshCustomerData();
  	function _refreshCustomerData() {
	
                    $http.get('/fetchallQuestions').then(function(response) {
		
                        $scope.data = response.data;
                        var newObj=[];
                        for(var i=0;i<response.data.length;i++){
							response.data[i]["possibilities"] = [{"answer":response.data[i].option1},{"answer":response.data[i].option2},{"answer":response.data[i].option3},{"answer":response.data[i].option4}];
							newObj[response.data[i].id]=response.data[i];
						}

						localStorage.setItem("Rajneesh",JSON.stringify(newObj));
                      // console.log(localStorage.getItem("Rajneesh"));
                        
                    });
     }
     	

     function selectAnswer(question,option){
		optionSelected[question]=option+1;
		vm.selectedOptionArray[question]={"selected":option};
	}
	
	function evaluateQuiz(){
		var jsonObjLen = calculateLength();
		var lenSelectedAnswerObj = Object.keys(optionSelected).length;
		if(jsonObjLen !== lenSelectedAnswerObj){
			console.log("Quiz Not completed");
			vm.error=true;
		}
		else{
			console.log("Quiz Completed");
			vm.finalise = true;
			vm.hideQuiz = true;
		}
	}
	
	function finaliseAnswers(){

		var jsonObj = JSON.parse(localStorage.getItem("Rajneesh"));
		var selectedAnswerObjKeys = Object.keys(optionSelected);
		var finalScore = 0;
		for(var i=0;i<selectedAnswerObjKeys.length;i++){
			if(optionSelected[selectedAnswerObjKeys[i]]===jsonObj[selectedAnswerObjKeys[i]].correctoption){
				vm.correctArray.push({"correct":true});
				vm.finalScore++;
			}
			else{
				vm.correctArray.push({"correct":false});
			}
			
		}
		console.log(vm.finalScore);
		console.log(vm.correctArray);
		vm.results  = true;
		vm.finalise = false;
	}
		
	function calculateLength(){
		var length = 0;
		for(var i=0;i<JSON.parse(localStorage.getItem("Rajneesh")).length;i++){
			if(JSON.parse(localStorage.getItem("Rajneesh"))[i]!== null){
				length++;
			}
		}
		return length;
	}
	
	function calculatePerc(){
		return vm.finalScore/vm.correctArray.length*100;
	}
	
	function reset(){
		optionSelected={};
		vm.correctArray=[];
		vm.selectedOptionArray=[];
		vm.results=false;
		vm.hideQuiz=false;
		vm.finalScore=0;
	}
}