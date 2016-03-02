package org.voovan.network.filter;

import org.voovan.network.IoFilter;
import org.voovan.network.IoSession;
import org.voovan.tools.TObject;

import java.nio.ByteBuffer;

/**
 * String 过滤器
 * 
 * @author helyho
 *
 * Voovan Framework.
 * WebSite: https://github.com/helyho/Voovan
 * Licence: Apache v2 License
 */
public class StringFilter implements IoFilter {

	@Override
	public Object encode(IoSession session,Object object) {
		if(object instanceof String){
			String sourceString = TObject.cast(object);
			return ByteBuffer.wrap(sourceString.getBytes());
		}
		return object;
	}

	@Override
	public Object decode(IoSession session,Object object) {
		if(object instanceof ByteBuffer){
			return byteBufferToString((ByteBuffer)object);
		}
		return object;
	}
	
	
	/**
	 * 将 ByteBuffer 转换成 String
	 * @param buf   byteBuffer 对象
	 * @return
	 */
	public static String byteBufferToString(ByteBuffer byteBuffer) {
		int size = byteBuffer.limit();
		byte[] byteBuf = new byte[size];
		byteBuffer.get(byteBuf);
		return new String(byteBuf);
	}
}
