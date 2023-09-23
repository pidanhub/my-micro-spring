package com.lfw.ioc.entrance;

import com.lfw.ioc.annotation.ZApplication;
import com.lfw.ioc.annotation.ZComponentScan;
import org.junit.jupiter.api.Test;

/*
 * @Author Zzs
 * @Description
 * @DateTime 2023/9/21 12:53
 */
@ZApplication
@ZComponentScan("com.lfw.ioc")
class ZApplicationEntranceTest {
	@Test
	void AMain () {
		ZApplicationEntrance.run(ZApplicationEntranceTest.class);
	}
	
}