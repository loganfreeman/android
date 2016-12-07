public class FileUtils {
	public static final String FOLDER_NAME = "xinlanedit";

	/**
	 * 获取存贮文件的文件夹路径
	 * 
	 * @return
	 */
	public static File createFolders() {
		File baseDir;
		if (android.os.Build.VERSION.SDK_INT < 8) {
			baseDir = Environment.getExternalStorageDirectory();
		} else {
			baseDir = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		}
		if (baseDir == null)
			return Environment.getExternalStorageDirectory();
		File aviaryFolder = new File(baseDir, FOLDER_NAME);
		if (aviaryFolder.exists())
			return aviaryFolder;
		if (aviaryFolder.isFile())
			aviaryFolder.delete();
		if (aviaryFolder.mkdirs())
			return aviaryFolder;
		return Environment.getExternalStorageDirectory();
	}

	public static File genEditFile(){
		return FileUtils.getEmptyFile("tietu"
				+ System.currentTimeMillis() + ".jpg");
	}

	public static File getEmptyFile(String name) {
		File folder = FileUtils.createFolders();
		if (folder != null) {
			if (folder.exists()) {
				File file = new File(folder, name);
				return file;
			}
		}
		return null;
	}

	/**
	 * 删除指定文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean deleteFileNoThrow(String path) {
		File file;
		try {
			file = new File(path);
		} catch (NullPointerException e) {
			return false;
		}

		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 保存图片
	 * 
	 * @param bitName
	 * @param mBitmap
	 */
	public static String saveBitmap(String bitName, Bitmap mBitmap) {
		File baseFolder = createFolders();
		File f = new File(baseFolder.getAbsolutePath(), bitName);
		FileOutputStream fOut = null;
		try {
			f.createNewFile();
			fOut = new FileOutputStream(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f.getAbsolutePath();
	}

	// 获取文件夹大小
	public static long getFolderSize(File file) throws Exception {
		long size = 0;
		try {
			File[] fileList = file.listFiles();
			for (int i = 0; i < fileList.length; i++) { // 如果下面还有文件
				if (fileList[i].isDirectory()) {
					size = size + getFolderSize(fileList[i]);
				} else {
					size = size + fileList[i].length();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	/** * 格式化单位 * * @param size * @return */
	public static String getFormatSize(double size) {
		double kiloByte = size / 1024d;
		int megaByte = (int) (kiloByte / 1024d);
		return megaByte + "MB";
	}

	/**
	 * 
	 * @Description:
	 * @Author 11120500
	 * @Date 2013-4-25
	 */
	public static boolean isConnect(Context context) {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {

		}
		return false;
	}

    public static byte[] readFile(File file) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];

            if (buffer.length != fis.read(buffer)) {
                return null;
            } else {
                return buffer;
            }

        } finally {
            close(fis);
        }
    }

    public static void writeFile(File file, String text, boolean append) throws IOException {
        if (file == null || text == null) {
            throw new IllegalArgumentException();
        }

        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new IOException("Could not create parent directory");
        }

        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Could not create file");
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(file, append);

            writer.write(text);

        } finally {
            close(writer);
        }
    }

    public static void delete(File file) throws IOException {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                delete(file1);
            }
        }
        if (!file.delete()) {
            throw new IOException("could not delete file " + file);
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                Cat.e(e);
            }
        }
    }

}// end
