package com.up72.common.freemarker;

import java.util.Map;

public interface ITemplateFlt {

	public abstract Map getModels();

	public abstract void setModels(Map models);

	public abstract String getSaveFileName();

	public abstract void setSaveFileName(String saveFileName);

	public abstract String getSavePath();

	public abstract void setSavePath(String savePath);

	public abstract String getRealPath();

	public abstract void setRealPath(String realPath);

	public abstract String getTemplatePath();

	public abstract void setTemplatePath(String templatePath);

	public abstract String getTemplateName();

	public abstract void setTemplateName(String templateName);

}