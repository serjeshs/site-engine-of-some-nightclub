<%@page import="temp.JSPHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false"%>

	<div id="content">
		<div class="inner pad1">
			<div class="container_12">
				<div class="wrapper h-pad">
					<div class="grid_7">
						<h2>Contact Info</h2>
						<div class="wrapper">
							<div class="grid_4 alpha">
								
							</div>
							<div class="grid_3 omega">
								<div class="contacts">
									<strong class="title">Компания</strong>
									Мы ещё не компания, у нас нет адреса :-), <br>
									Но я буду рад, если вы напишите мне, <br>
									E-mail: <a href="mailto:info@havefun.by" class="link">info@havefun.by</a>  
								</div>		
							</div>
						</div>
					</div>
					<div class="grid_4 prefix_1">
						<h2>Get In Touch</h2>
						<form id="contact-form" action="contactsemail" method="POST">
							<fieldset>
								<label><input type="text" value="Name"  name="Name" onFocus="if(this.value=='Name'){this.value=''}" onBlur="if(this.value==''){this.value='Name'}">	</label>
								<label><input type="text" value="Email" name="Email" onFocus="if(this.value=='Email'){this.value=''}" onBlur="if(this.value==''){this.value='Email'}">	</label>
								<label><input type="text" value="Phone" name="Phone" onFocus="if(this.value=='Phone'){this.value=''}" onBlur="if(this.value==''){this.value='Phone'}">	</label>
								<textarea name="Message" onFocus="if(this.value=='Message'){this.value=''}" onBlur="if(this.value==''){this.value='Message'}">Message</textarea>
								<a href="#" class="button1" onClick="document.getElementById('contact-form').reset()">clear</a>
								<input class="button1" type="submit" value="Submit">
							</fieldset>
						</form>
					</div>
				</div>
			</div>	
		</div>
	</div>