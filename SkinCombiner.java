/**
 * ****************************************************************************
 * Program Name:
 * Program Description:
 * Program Author:        Halvor Remole
 * Date Created:
 *
 * Change#        Change Date      Programmer Name        Description
 * -------        ------------     -------------------    ---------------------
 *****************************************************************************
 */
package skincombiner;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class SkinCombiner {

    
	    
    public static void main(String[] args) throws IOException {
	
	//PIECES OF THE BODY
	BufferedImage inner;
	BufferedImage innerHead;
	BufferedImage innerChest;
	BufferedImage innerArm;
	BufferedImage innerLeg;
	
	BufferedImage outer;
	BufferedImage outerHead;
	BufferedImage outerChest;
	BufferedImage outerArm;
	BufferedImage outerLeg;
	
	
	//SUBPIECES SPECIFCALLY FOR LET LIMBS
	BufferedImage leftTop;
	BufferedImage leftBottom;
	
	BufferedImage leftRight;
	BufferedImage leftFront;
	BufferedImage leftLeft;
	BufferedImage leftBack;
	
	BufferedImage combined = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
	Graphics2D combinedTemplate = combined.createGraphics();
	
	
	
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	Component foo = null;
	fileChooser.showOpenDialog(foo);
	inner = ImageIO.read(fileChooser.getSelectedFile());
	
	fileChooser.showOpenDialog(foo);
	outer = ImageIO.read(fileChooser.getSelectedFile());
	
	//inner = ImageIO.read(new File("inner_skin.png"));
	//outer = ImageIO.read(new File("outer_skin.png"));
	
	
	//GETS PIECES OF FIRST SKIN
	innerHead = inner.getSubimage(0, 0, 32, 16);
	innerChest = inner.getSubimage(16, 16, 24, 16);
	innerArm = inner.getSubimage(40, 16, 16, 16);
	innerLeg = inner.getSubimage(0, 16, 16, 16);
	
	//GETS PIECES OF SECOND SKIN
	outerHead = outer.getSubimage(0, 0, 32, 16);
	outerChest = outer.getSubimage(16, 16, 24, 16);
	outerArm = outer.getSubimage(40, 16, 16, 16);
	outerLeg = outer.getSubimage(0, 16, 16, 16);
	
	//SETS NON REVERSED LIMBS BEFORE THEY GET REVERSED
	combinedTemplate.drawImage(innerArm, 40, 16, null);
	combinedTemplate.drawImage(innerLeg, 0, 16, null);
	
	combinedTemplate.drawImage(outerLeg, 0, 32, null);
	combinedTemplate.drawImage(outerArm, 40, 32, null);
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//MAKES THE LEFT LIMBS PIECES	
	leftTop = innerArm.getSubimage(4, 0, 4, 4);
	leftBottom = innerArm.getSubimage(8, 0, 4, 4);
	
	leftRight = innerArm.getSubimage(0, 4, 4, 12);
	leftLeft = innerArm.getSubimage(8, 4, 4, 12);
	
	leftFront = innerArm.getSubimage(4, 4, 4, 12);
	leftBack = innerArm.getSubimage(12, 4, 4, 12);
	
	BufferedImage swapper = new BufferedImage(4, 12, BufferedImage.TYPE_INT_ARGB);	    //makes a temp swap storage
	Graphics2D swapTemp = swapper.createGraphics();
	Graphics2D right = leftRight.createGraphics();	    
	Graphics2D left = leftLeft.createGraphics();
	Graphics2D topMirror = leftTop.createGraphics();
	Graphics2D bottomMirror = leftBottom.createGraphics();
	Graphics2D backMirror = leftBack.createGraphics();
	Graphics2D frontMirror = leftFront.createGraphics();
	
	//PLAIN SWAPS
	swapTemp.drawImage(leftRight, 0, 0, null);	//sends right to swapTemp
	right.drawImage(leftLeft, 0, 0, null);		//sends left to right
	left.drawImage(swapper, 0, 0, null);		//sends swapper to left
	
	//HORIZONTAL FLIPS
	//g2.drawImage(image, x + width, y, -width, height, null);
	topMirror.drawImage(leftTop, 4, 0, -4, 4, null);
	bottomMirror.drawImage(leftBottom, 4, 0, -4, 4, null);
	backMirror.drawImage(leftBack, 4, 0, -4, 12, null);
	frontMirror.drawImage(leftFront, 4, 0, -4, 12, null);
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//APPARENTLY I GOT TO DO IT ALL AGAIN FOR THE OUTSIDE
	//MAKES THE LEFT LIMBS PIECES	
	BufferedImage leftTop2 = outerArm.getSubimage(4, 0, 4, 4);
	BufferedImage leftBottom2 = outerArm.getSubimage(8, 0, 4, 4);
	
	BufferedImage leftRight2 = outerArm.getSubimage(0, 4, 4, 12);
	BufferedImage leftLeft2 = outerArm.getSubimage(8, 4, 4, 12);
	
	BufferedImage leftFront2 = outerArm.getSubimage(4, 4, 4, 12);
	BufferedImage leftBack2 = outerArm.getSubimage(12, 4, 4, 12);
	
	//APPARENTLY I NEEDED NEW ONES
	BufferedImage swapper2 = new BufferedImage(4, 12, BufferedImage.TYPE_INT_ARGB);	    //makes a temp swap storage
	Graphics2D swapTemp2 = swapper2.createGraphics();
	Graphics2D right2 = leftRight2.createGraphics();	    
	Graphics2D left2 = leftLeft2.createGraphics();
	Graphics2D topMirror2 = leftTop2.createGraphics();
	Graphics2D bottomMirror2 = leftBottom2.createGraphics();
	Graphics2D backMirror2 = leftBack2.createGraphics();
	Graphics2D frontMirror2 = leftFront2.createGraphics();
	
	//PLAIN SWAPS
	swapTemp2.drawImage(leftRight2, 0, 0, null);	//sends right to swapTemp
	right2.drawImage(leftLeft2, 0, 0, null);		//sends left to right
	left2.drawImage(swapper2, 0, 0, null);		//sends swapper to left
	
	//HORIZONTAL FLIPS
	//g2.drawImage(image, x + width, y, -width, height, null);
	topMirror2.drawImage(leftTop2, 4, 0, -4, 4, null);
	bottomMirror2.drawImage(leftBottom2, 4, 0, -4, 4, null);
	backMirror2.drawImage(leftBack2, 4, 0, -4, 12, null);
	frontMirror2.drawImage(leftFront2, 4, 0, -4, 12, null);
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//WHO WANTS A REPEAT FOR THE LEGS?
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//MAKES THE LEFT LEG PIECES	
	BufferedImage leftTop3 = innerLeg.getSubimage(4, 0, 4, 4);
	BufferedImage leftBottom3 = innerLeg.getSubimage(8, 0, 4, 4);
	
	BufferedImage leftRight3 = innerLeg.getSubimage(0, 4, 4, 12);
	BufferedImage leftLeft3 = innerLeg.getSubimage(8, 4, 4, 12);
	
	BufferedImage leftFront3 = innerLeg.getSubimage(4, 4, 4, 12);
	BufferedImage leftBack3 = innerLeg.getSubimage(12, 4, 4, 12);
	
	BufferedImage swapper3 = new BufferedImage(4, 12, BufferedImage.TYPE_INT_ARGB);	    //makes a temp swap storage
	Graphics2D swapTemp3 = swapper3.createGraphics();
	Graphics2D right3 = leftRight3.createGraphics();	    
	Graphics2D left3 = leftLeft3.createGraphics();
	Graphics2D topMirror3 = leftTop3.createGraphics();
	Graphics2D bottomMirror3 = leftBottom3.createGraphics();
	Graphics2D backMirror3 = leftBack3.createGraphics();
	Graphics2D frontMirror3 = leftFront3.createGraphics();
	
	//PLAIN SWAPS
	swapTemp3.drawImage(leftRight3, 0, 0, null);	//sends right to swapTemp
	right3.drawImage(leftLeft3, 0, 0, null);		//sends left to right
	left3.drawImage(swapper3, 0, 0, null);		//sends swapper to left
	
	//HORIZONTAL FLIPS
	//g2.drawImage(image, x + width, y, -width, height, null);
	topMirror3.drawImage(leftTop3, 4, 0, -4, 4, null);
	bottomMirror3.drawImage(leftBottom3, 4, 0, -4, 4, null);
	backMirror3.drawImage(leftBack3, 4, 0, -4, 12, null);
	frontMirror3.drawImage(leftFront3, 4, 0, -4, 12, null);
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//APPARENTLY I GOT TO DO IT ALL AGAIN FOR THE OUTSIDE
	//MAKES THE LEFT LIMBS PIECES	
	BufferedImage leftTop4 = outerLeg.getSubimage(4, 0, 4, 4);
	BufferedImage leftBottom4 = outerLeg.getSubimage(8, 0, 4, 4);
	
	BufferedImage leftRight4 = outerLeg.getSubimage(0, 4, 4, 12);
	BufferedImage leftLeft4 = outerLeg.getSubimage(8, 4, 4, 12);
	
	BufferedImage leftFront4 = outerLeg.getSubimage(4, 4, 4, 12);
	BufferedImage leftBack4 = outerLeg.getSubimage(12, 4, 4, 12);
	
	//APPARENTLY I NEEDED NEW ONES
	BufferedImage swapper4 = new BufferedImage(4, 12, BufferedImage.TYPE_INT_ARGB);	    //makes a temp swap storage
	Graphics2D swapTemp4 = swapper4.createGraphics();
	Graphics2D right4 = leftRight4.createGraphics();	    
	Graphics2D left4 = leftLeft4.createGraphics();
	Graphics2D topMirror4 = leftTop4.createGraphics();
	Graphics2D bottomMirror4 = leftBottom4.createGraphics();
	Graphics2D backMirror4 = leftBack4.createGraphics();
	Graphics2D frontMirror4 = leftFront4.createGraphics();
	
	//PLAIN SWAPS
	swapTemp4.drawImage(leftRight4, 0, 0, null);	//sends right to swapTemp
	right4.drawImage(leftLeft4, 0, 0, null);		//sends left to right
	left4.drawImage(swapper4, 0, 0, null);		//sends swapper to left
	
	//HORIZONTAL FLIPS
	//g2.drawImage(image, x + width, y, -width, height, null);
	topMirror4.drawImage(leftTop4, 4, 0, -4, 4, null);
	bottomMirror4.drawImage(leftBottom4, 4, 0, -4, 4, null);
	backMirror4.drawImage(leftBack4, 4, 0, -4, 12, null);
	frontMirror4.drawImage(leftFront4, 4, 0, -4, 12, null);
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//PLACES THE PIECES ON THE MAIN TEMPLATE
	combinedTemplate.drawImage(innerHead, 0, 0, null);
	combinedTemplate.drawImage(outerHead, 32, 0, null);
	combinedTemplate.drawImage(innerChest, 16, 16, null);
	combinedTemplate.drawImage(outerChest, 16, 32, null);
	
	combinedTemplate.drawImage(innerLeg, 16, 48, null);	//left leg	(was reversed above)
	combinedTemplate.drawImage(innerArm, 32, 48, null);	//left arm
	
	combinedTemplate.drawImage(outerLeg, 0, 48, null);	//left leg	(was reversed above)
	combinedTemplate.drawImage(outerArm, 48, 48, null);	//left arm	    
	
	
	
	File out = new File("CombinedSkin.png");
	ImageIO.write(combined, "png", out);
    }
    
}
