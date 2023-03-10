package com.sk.market.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sk.market.application.CartService;

@Controller
public class ScannerController {
	
	private final CartService cartService;

	public ScannerController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping("/")
	public String scanProduct(Model model) {
		model.addAttribute("upc", "");
		model.addAttribute("total", cartService.total().toPlainString());
		return "scan";
	}
	
	@PostMapping("/")
	public String addProduct(@RequestParam(name = "upc", defaultValue = "") String upc) {
		Assert.hasText(upc, "upc is not empty!");
		cartService.addProduct(upc);
		return "redirect:/";
	}
}