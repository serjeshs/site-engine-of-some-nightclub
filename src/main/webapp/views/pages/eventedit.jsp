<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="springtags" %>
<%@ page session="false"%>


	<div id="content">
		<div class="inner pad1">
			<div class="container_12">
				<div class="wrapper h-pad">
					<div class="grid_7">
						<h2>${lang_template.please_enter_event_info}.</h2>
						<div class="wrapper">
								<figure class="img-indent">
								<div align="center" ><img src="/<c:out value="${appName}" /><c:out value="${event.imageUri}" />" height="400" alt=""></div>
								</figure>
								
							</div>
						<br>
						<form id="contact-form" action="/<c:out value="${appName}" />/event/edit" method="POST" enctype="multipart/form-data">
						<input type="hidden" name="id" value="<c:out value="${eventid}"/>" />
						<fieldset>
						
							<br>
							<label>
							
							${lang_template.upload_new_image}
							<br>
							<input type="file" name="photo" accept="image/*">
							<br>
							</label>
							<br><br>
							
							<label>
							${lang_template.name_event}<br>
							<input type="text" name="name" value="<c:out value="${event.name}"/>">
							</label>
							${lang_template.description_event}<br>
							<textarea name="description"><c:out value="${event.description}"/></textarea><br><br>
							${lang_template.cost_min_int} .<br>
							<label><input type="text" name="cost" value="<c:out value="${event.cost}"/>">	</label>
							${lang_template.cost_min_string} .<br>
							<textarea name="costText"><c:out value="${event.costText}"/></textarea><br><br>

								<br>${lang_template.place}<br>
								<div class="input-group" style="margin: 20px 0px;">
									<input type="text" class="form-control" id="remote_input"
										placeholder="Пачніце ўводзіць мейсца" name="adsd" value='<c:out value="${event.place_Name}"></c:out>'>
									<input id="id_hidden" type="hidden" name="Place_id" value='<c:out value="${event.place}"></c:out>'/>
										 <span
										class="input-group-btn">
										<button id="open" class="btn btn-default" type="button">
											<span class="caret"></span>
										</button>
									</span>
								</div>
								<label>
								${lang_template.start_time}<br>
								<input id="datetimepicker1" type="text" name="startEvent" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${event.startEvent}" />">	
							</label>
							
							
							
							<label>
							${lang_template.end_time}<br>
							<input id="datetimepicker2" type="text" name="endEvent" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${event.endEvent}" />">	
							</label>
								<input type="submit" value="Submit" class="button1">
							</fieldset>
						</form>
					</div>
				</div>
			</div>	
		</div>
	</div>