package com.spring.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.models.Product;
import com.spring.models.ProductDto;
import com.spring.services.ProductsRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/sanpham")
public class ProductsController {
	@Autowired
	private ProductsRepository repo;

	@GetMapping({ "", "/" })
	public String showProductsList(Model model) {
		List<Product> products = repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
		model.addAttribute("products", products);
		return "products/index";
	}

	@GetMapping({ "/them" })
	public String showCreatePage(Model model) {
		ProductDto productDto = new ProductDto();
		model.addAttribute("productDto", productDto);
		return "products/CreateProduct";
	}

	@PostMapping({ "/them" })
	public String createProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result) {
		if (productDto.getAnh().isEmpty()) {
			result.addError(new FieldError("productDto", "anh", "Chưa chọn file ảnh"));
		}
		if (result.hasErrors()) {
			return "products/CreateProduct";
		}

		// Save image file
		MultipartFile image = productDto.getAnh();
		Date createAt = new Date();
		String storangeFileName = createAt.getTime() + "_" + image.getOriginalFilename();
		try {
			String uploadDir = "public/images/";
			Path uploadPath = Paths.get(uploadDir);

			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			try (InputStream inputstream = image.getInputStream()) {
				Files.copy(inputstream, Paths.get(uploadDir + storangeFileName), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			System.out.println("Lỗi: " + e);
		}

		Product product = new Product();
		product.setTen_hang(productDto.getTen_hang());
		product.setGia_nhap(productDto.getGia_nhap());
		product.setGia_ban(productDto.getGia_ban());
		product.setDvt(productDto.getDvt());
		product.setSlt(productDto.getSlt());
		product.setLoai_hang_hoa(productDto.getLoai_hang_hoa());
		product.setGhi_chu(productDto.getGhi_chu());
		product.setNgay_nhap(createAt);
		product.setAnh(storangeFileName);

		repo.save(product);

		return "redirect:/sanpham";
	}

	@GetMapping("/sua")
	public String showEditPage(Model model, @RequestParam int id) {
		try {
			Product product = repo.findById(id).get();
			model.addAttribute("product", product);

			ProductDto productDto = new ProductDto();
			productDto.setTen_hang(product.getTen_hang());
			productDto.setGia_nhap(product.getGia_nhap());
			productDto.setGia_ban(product.getGia_ban());
			productDto.setDvt(product.getDvt());
			productDto.setSlt(product.getSlt());
			productDto.setLoai_hang_hoa(product.getLoai_hang_hoa());
			productDto.setGhi_chu(product.getGhi_chu());

			model.addAttribute("productDto", productDto);
		} catch (Exception e) {
			System.out.println("Lỗi: " + e);
			return "redirect:/sanpham";
		}

		return "products/EditProduct";
	}

	@PostMapping({ "/sua" })
	public String updateProduct(Model model, @RequestParam int id, @Valid @ModelAttribute ProductDto productDto,
			BindingResult result) {
		try {
			Product product = repo.findById(id).get();
			model.addAttribute("product", product);
			if (result.hasErrors()) {
				return "products/EditProduct";
			}
			if (!productDto.getAnh().isEmpty()) {
				// delete old image
				String uploadDir = "public/images/";
				Path oldImagePath = Paths.get(uploadDir + product.getAnh());
				try {
					Files.delete(oldImagePath);
				} catch (Exception e) {
					System.out.println("Lỗi: " + e);
				}

				// save new image file
				MultipartFile image = productDto.getAnh();
				Date createAt = new Date();
				String storangeFileName = createAt.getTime() + "_" + image.getOriginalFilename();
				try (InputStream inputstream = image.getInputStream()) {
					Files.copy(inputstream, Paths.get(uploadDir + storangeFileName),
							StandardCopyOption.REPLACE_EXISTING);
				}
				product.setAnh(storangeFileName);
			}

			product.setTen_hang(productDto.getTen_hang());
			product.setGia_nhap(productDto.getGia_nhap());
			product.setGia_ban(productDto.getGia_ban());
			product.setDvt(productDto.getDvt());
			product.setSlt(productDto.getSlt());
			product.setLoai_hang_hoa(productDto.getLoai_hang_hoa());
			product.setGhi_chu(productDto.getGhi_chu());

			repo.save(product);
		} catch (Exception e) {
			System.out.println("Lỗi: " + e);
		}

		return "redirect:/sanpham";
	}

	@GetMapping("/xoa")
	public String deleteProduct(@RequestParam int id) {
		try {
			Product product = repo.findById(id).get();

			// Delete product image
			Path imagePath = Paths.get("public/images/" + product.getAnh());
			try {
				Files.delete(imagePath);
			} catch (Exception e) {
				System.out.println("Lỗi: " + e);
			}

			repo.delete(product);
		} catch (Exception e) {
			System.out.println("Lỗi: " + e);
		}
		return "redirect:/sanpham";
	}
}
