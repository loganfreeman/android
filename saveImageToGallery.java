    private void saveImageToGallery(Bitmap memeBitmap) {

        if (!originalImage) {

            // save bitmap to file
            File imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + APP_PICTURE_DIRECTORY), memeBitmap + FILE_SUFFIX_JPG);

            try {
                // create outputstream, compress image and write to file, flush and close outputstream
                FileOutputStream fos = new FileOutputStream(imageFile);
                memeBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                Toast.makeText(this, getResources().getText(R.string.save_image_failed).toString(), Toast.LENGTH_SHORT).show();
            }

            // Create intent to request newly created file to be scanned, pass picture uri and broadcast intent
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            mediaScanIntent.setData(Uri.fromFile(imageFile));
            sendBroadcast(mediaScanIntent);
            
            Toast.makeText(this, getResources().getText(R.string.save_image_succeeded).toString(), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, getResources().getText(R.string.add_meme_message).toString(), Toast.LENGTH_SHORT).show();
        }
    }
