<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="date" class="java.util.Date" />

<form action="EditReport" method="POST">
	<input type="hidden" id="reportTitle" value="${sessionScope.report.reportTitle}">
	<input type="hidden" id="templateId" value="${sessionScope.report.templateId}">	
			<fieldset>
				<legend class="left-label">
					2.Section I: <input type="text" name="sec1Title" value="${sessionScope.template.sec1Title}" disabled/>
				</legend>
				<fieldset>  
					<c:forEach var="s1m" items="${sessionScope.section1Map}" >
						<input type="text" name="s1criteria" value="${s1m.key}" disabled/>
						<select name="s1eval" class="evaluation" onchange="calculateSum()">
							<c:forEach var="i" begin="1" end="${s1m.value}">
							    <option value="${i}">${i}</option>
							    	<!--<c:if test="${i}"> selected </c:if>-->
							   
							</c:forEach>
						</select>
						<br/>
					</c:forEach>
					<textarea name="s1comment" id="s1comment" class="comments" cols="30" rows="10" placeholder="${sessionScope.report.comment1}" disabled></textarea>
				</fieldset>
				<hr />

				<legend class="left-label">
					3.Section II: <input type="text" name="sec2Title" value="${sessionScope.template.sec2Title}" readonly/>
				</legend>
				<fieldset>  
					<c:forEach var="s2m" items="${sessionScope.section2Map}" >
						<input type="text" name="s2criteria" value="${s2m.key}" disabled/>
						<select name="s2eval" class="evaluation" onchange="calculateSum()">
							<c:forEach var="i" begin="1" end="${s2m.value}">
							    <option value="${i}">${i}</option>
							    <!--<c:if test="${i == selectedEvals.get(1)}"> "selected" </c:if>-->
							    
							</c:forEach>
						</select>
						<br/>
					</c:forEach>
					<textarea name="s2comment" id="s2comment" class="comments" cols="30" rows="10" placeholder="${report.comment2}" disabled></textarea>
				</fieldset>
				<hr />

				<legend>
					3.Section III:<input type="text" name="sec3Title" value="${sessionScope.template.sec3Title}" readonly/>
				</legend>
				<fieldset>  
					<c:forEach var="s3m" items="${sessionScope.section3Map}" >
						<input type="text" name="s3criteria" value="${s3m.key}" disabled/>
						<select name="s3eval" class="evaluation" onchange="calculateSum()">
							<c:forEach var="i" begin="1" end="${s3m.value}">
							    <option value="${i}">${i}</option>
							    <!--<c:if test="${i == selectedEvals.get(2)}"> "selected" </c:if>-->
							    
							</c:forEach>
						</select>
						<br/>
					</c:forEach>
					<textarea name="s3comment" id="s3comment" class="comments" cols="30" rows="10" 
								placeholder="${sessionScope.report.comment3}" readonly></textarea>
								
				</fieldset>
			</fieldset>
				<input type="text" id="sumBox" value="${sessionScope.report.evaluation}" readonly></input><p> / ${sessionScope.maxEvaluation}</p>
			<input type="hidden" id="sumBoxHidden" value="${sessionScope.report.evaluation}" name="evaluationTotal"></input>
				<hr />
			


<input type="submit" value="Edit Report" id="edit"> <!-- submit send to servlet, servlet redirects back to here with 
														parameter that servlet tests for to enable-->
<input type="reset" value="Back" onclick="toggleDiv()"><!-- also disables field using javascript -->
</form>
			
<script>	    	
       //function for getting sum of evaluations and populating
       function calculateSum()
       {
    	   var sum = 0;
    	   var x = document.getElementsByClassName("evaluation");
    	   for (var i=0; i<x.length; i++)
    		   {
    		   		sum = sum + eval(x[i].value);
    		   }
    	   document.getElementById("sumBox").value = sum;
       }

       
       
</script>