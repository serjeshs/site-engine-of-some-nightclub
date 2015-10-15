package by.havefun.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import by.havefun.entity.Place;

@Controller
public class PlaceController extends AbstractController{

	@RequestMapping(value = "places")
	public @ResponseBody List<Place> register(Model model, Principal principal,Integer regionId, String s) {
		String email = null;
		if ( principal != null)
			email = principal.getName();
		List<Place> places = placeDAO.getPlaces(email,regionId,s);
		return places;
	}
}
