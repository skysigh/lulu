package org.skysigh.lulu.admin.vo;

import java.io.Serializable;
import java.util.List;

import org.skysigh.lulu.admin.po.Specification;
import org.skysigh.lulu.admin.po.SpecificationOption;

public class SpecificationVo implements Serializable {

	private static final long serialVersionUID = -5525331684222697781L;

	private Specification spec;
	private List<SpecificationOption> specOptions;

	public Specification getSpec() {
		return spec;
	}

	public void setSpec(Specification spec) {
		this.spec = spec;
	}

	public List<SpecificationOption> getSpecOptions() {
		return specOptions;
	}

	public void setSpecOptions(List<SpecificationOption> specOptions) {
		this.specOptions = specOptions;
	}
}
