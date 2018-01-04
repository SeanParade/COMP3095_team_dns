<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="date" class="java.util.Date" />

				
				<legend class="left-label">
					2.Section I: <input type="text" name="sec1Title" value="${template.sec1Title}" disabled/>
				</legend>
				<fieldset>  
					<c:forEach var="s1m" items="${section1Map}" >
						<input type="text" name="s1criteria" value="${s1m.key}" disabled/>
						<select name="s1eval" class="evaluation" onchange="calculateSum()">
							<c:forEach var="i" begin="1" end="${s1m.value}">
							    <option value="${i}">${i}</option>
							</c:forEach>
						</select>
						<br/>
					</c:forEach>
					<textarea name="s1comment" id="s1comment" class="comments" cols="30" rows="10" value="${report.comment1}" disabled></textarea>
				</fieldset>
				<hr />

				<legend class="left-label">
					3.Section II: <input type="text" name="sec2Title" value="${template.sec2Title}" disabled/>
				</legend>
				<fieldset>  
					<c:forEach var="s2m" items="${section2Map}" >
						<input type="text" name="s2criteria" value="${s2m.key}" disabled/>
						<select name="s2eval" class="evaluation" onchange="calculateSum()">
							<c:forEach var="i" begin="1" end="${s2m.value}">
							    <option value="${i}">${i}</option>
							</c:forEach>
						</select>
						<br/>
					</c:forEach>
					<textarea name="s2comment" id="s2comment" class="comments" cols="30" rows="10" 
					            placeholder="Please enter a comment here"></textarea>
				</fieldset>
				<hr />

				<legend>
					3.Section III:<input type="text" name="sec3Title" value="${template.sec3Title}" disabled/>
				</legend>
				<fieldset>  
					<c:forEach var="s3m" items="${section3Map}" >
						<input type="text" name="s3criteria" value="${s3m.key}" disabled/>
						<select name="s3eval" class="evaluation" onchange="calculateSum()">
							<c:forEach var="i" begin="1" end="${s3m.value}">
							    <option value="${i}">${i}</option>
							</c:forEach>
						</select>
						<br/>
					</c:forEach>
					<textarea name="s3comment" id="s3comment" class="comments" cols="30" rows="10" 
								placeholder="Please enter a comment here"></textarea>
								
				</fieldset>
				<hr />
				<input type="submit" value="Submit" /> <input type="reset"
					value="Cancel" />
			</fieldset>

<form action="" method="POST">
<input type="button" value="Edit Report" onclick=""> <!-- onclick hide this div, show edit div -->
<input type="reset" value="Back">
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
       // disable drop down list based on selection of employee rb or group rb
       function disable(element){
    	if(element.id == "groupType") 
    		{
    			//disable employee dropdown
				 document.getElementById("ddlEmployee").disabled = true;   		
    			//enable group dropdown
    			document.getElementById("ddlGroup").disabled = false;
    		}
    	if(element.id == "employeeType")
    		{
    			//disable group dropdown
    			document.getElementById("ddlGroup").disabled = true;   		
				//enable employee dropdown
				document.getElementById("ddlEmployee").disabled = false;
    			
    		}
    	
       }
       
       
</script>