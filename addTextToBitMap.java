    private void addTextToBitMap(Bitmap viewBitmap, String topText, String bottomText) {

        // get dimensions of image
        int bitmapWidth = viewBitmap.getWidth();
        int bitmapHeight = viewBitmap.getHeight();

        // create a canvas that uses the bitmap as its base
        Canvas pictureCanvas = new Canvas(viewBitmap);

        // create paint object with font parameters

        Typeface tf = Typeface.create(HELVETICA_FONT, Typeface.BOLD);

        int textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                18,
                getResources().getDisplayMetrics());

        Paint textPaint = new Paint();
        textPaint.setTextSize(textSize);
        textPaint.setColor(Color.WHITE);
        textPaint.setTypeface(tf);
        textPaint.setTextAlign(Paint.Align.CENTER);

        Paint textPaintOutline = new Paint();
        textPaintOutline.setAntiAlias(true);
        textPaintOutline.setTextSize(textSize);
        textPaintOutline.setColor(Color.BLACK);
        textPaintOutline.setTypeface(tf);
        textPaintOutline.setStyle(Paint.Style.STROKE);
        textPaintOutline.setTextAlign(Paint.Align.CENTER);
        textPaintOutline.setStrokeWidth(8);

        float xPos = bitmapWidth / 2;
        float yPos = bitmapHeight / 7;

        pictureCanvas.drawText(topText, xPos, yPos, textPaintOutline);
        pictureCanvas.drawText(topText, xPos, yPos, textPaint);

        yPos = bitmapHeight - bitmapHeight / 14;

        pictureCanvas.drawText(bottomText, xPos, yPos, textPaintOutline);
        pictureCanvas.drawText(bottomText, xPos, yPos, textPaint);
    }
