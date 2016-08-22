package com.stas.tsepa.vkbirthday.domain.repository;

import com.stas.tsepa.vkbirthday.domain.model.Template;

import java.util.List;

/**
 * Created by Tsepa Stas
 * Email tsepa.stas@gmail.com
 */
public interface TemplateRepository {
    List<Template> getAll();
}
