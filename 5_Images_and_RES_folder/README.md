### These programs, along with the accompanying video at ____, show how to load images, use a resource folder, and make Jar files. 
These examples are using Swing, but they will also work with HSA2.

**Update**

The second method of loading an image from a resource folder works with both ImageIO.read as well as ImageIcon

```
  /* Requires a / at the beginning of the filename (for absolute path) */
	Image loadImageR2(String fn) {
		Image image = null;
		
		//1. Using ImageIcon
		ImageIcon icon = new ImageIcon(this.getClass().getResource(fn));
		image = icon.getImage();
		
		//2. Using ImageIO.read
		try {
			image = ImageIO.read(this.getClass().getResource(fn));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
```
