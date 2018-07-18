package kr.co.miracom.dbist.util;

import kr.co.miracom.dbist.metadata.Column;

public interface ValueGenerator {
	public Object generate(Object data, Column column) throws Exception;
}
