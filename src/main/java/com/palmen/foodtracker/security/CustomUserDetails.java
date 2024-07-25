package com.palmen.foodtracker.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.palmen.foodtracker.models.Rol;
import com.palmen.foodtracker.models.Usuario;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public CustomUserDetails(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		   Set<Rol> roles = usuario.getRoles();
	        return roles.stream()
	                .map(rol -> new SimpleGrantedAuthority(rol.getNombreRol()))
	                .collect(Collectors.toSet());
	}

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getNombreUsuario();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
