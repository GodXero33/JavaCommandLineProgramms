class ShopArrays {
	public static int[] getCopy (int[] arr) {
		final int n = arr.length;
		final int[] newArr = new int[n];

		for (int i = 0; i < n; i++) {
			newArr[i] = arr[i];
		}

		return newArr;
	}

	public static int[] getSortedArrayIndices (int[] arr, boolean order) {
		final int n = arr.length;
		int[] newArr = getCopy(arr);
		int[] indices = new int[n];

		for (int i = 0; i < n; i++) {
			indices[i] = i;
		}

		if (order) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < newArr.length - 1 - i; j++) {
					if (newArr[j] > newArr[j + 1]) {
						newArr[j] ^= newArr[j + 1];
						newArr[j + 1] ^= newArr[j];
						newArr[j] ^= newArr[j + 1];
	
						indices[j] ^= indices[j + 1];
						indices[j + 1] ^= indices[j];
						indices[j] ^= indices[j + 1];
					}
				}
			}
		} else {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < newArr.length - 1 - i; j++) {
					if (newArr[j] < newArr[j + 1]) {
						newArr[j] ^= newArr[j + 1];
						newArr[j + 1] ^= newArr[j];
						newArr[j] ^= newArr[j + 1];
	
						indices[j] ^= indices[j + 1];
						indices[j + 1] ^= indices[j];
						indices[j] ^= indices[j + 1];
					}
				}
			}
		}

		return indices;
	}
}
