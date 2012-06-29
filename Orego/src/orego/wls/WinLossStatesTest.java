package orego.wls;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class WinLossStatesTest {

	private WinLossStates states;
	
	@Before
	public void setUp() throws Exception {
		states = new WinLossStates();
	}

	@Test
	public void testTables() {
		// TODO The last 3 zeroes are irrelevant
//		assertTrue(Arrays.equals(new int[]{208, 15, 17, 18, 19, 21, 22, 23, 25, 26
//				, 27, 30, 33, 36, 40, 44, 51, 40, 28, 29
//				, 58, 32, 34, 35, 69, 37, 39, 42, 36, 38
//				, 46, 88, 41, 49, 43, 47, 54, 48, 54, 52
//				, 60, 50, 55, 53, 66, 112, 59, 56, 57, 63
//				, 63, 73, 62, 61, 68, 65, 64, 67, 85, 70
//				, 74, 77, 71, 77, 72, 76, 83, 75, 80, 98
//				, 79, 78, 86, 92, 89, 82, 84, 86, 87, 90
//				, 93, 122, 95, 96, 91, 103, 95, 94, 118, 100
//				, 97, 99, 106, 101, 97, 102, 107, 104, 121, 105
//				, 108, 109, 110, 120, 111, 114, 119, 117, 116, 115
//				, 114, 113, 123, 131, 131, 130, 129, 128, 124, 127
//				, 126, 125, 141, 135, 132, 133, 134, 136, 137, 138
//				, 139, 140, 150, 147, 146, 155, 145, 144, 143, 142
//				, 142, 165, 148, 149, 151, 152, 153, 157, 149, 154
//				, 161, 156, 158, 160, 159, 168, 162, 164, 163, 169
//				, 167, 170, 166, 169, 173, 184, 171, 174, 180, 175
//				, 179, 174, 208, 176, 177, 178, 183, 182, 181, 185
//				, 187, 183, 186, 188, 195, 190, 189, 193, 191, 192
//				, 194, 196, 194, 199, 198, 202, 197, 200, 201, 204
//				, 203, 205, 209, 207, 207, 206, 210, 211, 222, 213
//				, 212, 214, 215, 217, 216, 220, 218, 220, 219, 221
//				, 223, 224, 229, 226, 225, 241, 227, 228, 230, 233
//				, 231, 232, 234, 237, 235, 236, 240, 238, 239, 240
//				, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250
//				, 251, 252, 252}, states.WIN));
//		assertTrue(Arrays.equals(new int[] {122, 1, 1, 2, 3, 4, 5, 6, 7, 8
//				, 9, 10, 11, 12, 13, 14, 15, 13, 17, 18
//				, 16, 19, 21, 22, 20, 23, 25, 26, 12, 28
//				, 27, 24, 29, 30, 32, 34, 33, 35, 33, 37
//				, 36, 38, 39, 41, 40, 31, 42, 43, 47, 46
//				, 46, 44, 48, 50, 49, 52, 53, 56, 51, 55
//				, 54, 59, 57, 59, 61, 62, 60, 64, 63, 58
//				, 65, 67, 70, 66, 68, 72, 71, 70, 75, 76
//				, 77, 45, 79, 74, 78, 73, 79, 82, 69, 80
//				, 84, 87, 83, 86, 84, 90, 89, 91, 85, 94
//				, 93, 95, 97, 92, 99, 104, 96, 100, 101, 102
//				, 104, 105, 88, 111, 111, 110, 109, 108, 98, 107
//				, 106, 103, 112, 118, 121, 120, 119, 117, 116, 115
//				, 114, 113, 125, 126, 127, 124, 128, 129, 130, 131
//				, 131, 123, 140, 139, 138, 137, 136, 134, 139, 142
//				, 133, 143, 144, 145, 148, 132, 149, 146, 151, 156
//				, 152, 147, 154, 156, 153, 135, 159, 158, 150, 162
//				, 157, 158, 122, 160, 163, 166, 167, 169, 171, 164
//				, 161, 167, 175, 174, 155, 173, 178, 170, 177, 181
//				, 176, 182, 176, 179, 183, 168, 186, 189, 188, 185
//				, 192, 191, 180, 190, 190, 196, 197, 194, 141, 187
//				, 200, 198, 203, 193, 201, 199, 205, 199, 206, 210
//				, 204, 212, 165, 207, 215, 217, 211, 214, 216, 184
//				, 218, 219, 221, 195, 224, 225, 213, 202, 209, 213
//				, 217, 220, 223, 226, 227, 228, 230, 231, 232, 234
//				, 235, 236, 238}, states.LOSS));
	}

}