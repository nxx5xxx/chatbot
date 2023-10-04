package com.okbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okbs.collections.MenuDocument;
import com.okbs.repository.MenuRepository;


@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuRepository menuRepository;
	
	@Override
	public MenuDocument selectMenu(String title) {
	return menuRepository.findByTitles(title);
}
	
}
