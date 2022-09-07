package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.service.IGestorService;
import com.uce.edu.demo.service.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IGestorService gestorService;

	@GetMapping("/buscar")
	public String buscarProductosTodos(Model modelo) {

		List<Producto> listaProductos = this.productoService.buscarTodos();
		modelo.addAttribute("productos", listaProductos);
		return "vistaListaProductos";
	}

	@GetMapping("/nuevoProducto")
	public String nuevoProducto(Producto producto) {
		return "vistaNuevoProducto";
	}

	@PostMapping("/insertar")
	public String insertarProducto(Producto producto) {
		this.productoService.ingresarProducto(producto);
		return "redirect:/productos/buscar";
	}
}
