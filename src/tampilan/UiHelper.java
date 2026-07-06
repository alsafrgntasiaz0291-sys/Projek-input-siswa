package tampilan;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

public final class UiHelper {
    public static final Color DARK_GREEN = new Color(0, 145, 0);
    public static final Color SOFT_GREEN = new Color(204, 255, 153);
    public static final Color PALE_YELLOW = new Color(255, 255, 204);

    private UiHelper() {
    }

    public static void prepareFrame(JFrame frame) {
        normalizeComponents(frame.getContentPane());
        frame.pack();
        fitToScreen(frame);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void preparePanel(Container panel) {
        normalizeComponents(panel);
    }

    public static JLabel imageLabel(Class<?> owner, String resource, int maxWidth, int maxHeight) {
        JLabel label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        URL url = owner.getResource(resource);
        if (url != null) {
            label.setIcon(scaleIcon(new ImageIcon(url), maxWidth, maxHeight));
        }

        return label;
    }

    public static javax.swing.JPanel filledImagePanel(Class<?> owner, String resource) {
        URL url = owner.getResource(resource);
        final Image image = url == null ? null : new ImageIcon(url).getImage();

        return new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
    }

    public static void styleButton(AbstractButton button) {
        button.setFont(new Font("Times New Roman", Font.BOLD, 18));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setIconTextGap(12);
        button.setPreferredSize(new Dimension(230, 58));
    }

    public static JLabel title(String text, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Times New Roman", Font.ITALIC, size));
        label.setForeground(Color.WHITE);
        return label;
    }

    public static javax.swing.JPanel pageHeader(Class<?> owner, String titleText, AbstractButton... buttons) {
        javax.swing.JPanel header = new javax.swing.JPanel(new java.awt.BorderLayout(24, 0));
        header.setBackground(DARK_GREEN);
        header.setBorder(BorderFactory.createEmptyBorder(14, 28, 14, 28));

        javax.swing.JPanel left = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 18, 0));
        left.setOpaque(false);
        left.add(imageLabel(owner, "/images/logo (1).png", 120, 120));
        left.add(title(titleText, 26));

        javax.swing.JPanel right = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 18, 24));
        right.setOpaque(false);
        for (AbstractButton button : buttons) {
            styleButton(button);
            right.add(button);
        }

        header.add(left, java.awt.BorderLayout.WEST);
        header.add(right, java.awt.BorderLayout.EAST);
        return header;
    }

    private static void normalizeComponents(Component component) {
        normalizeFont(component);
        normalizeIcon(component);

        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                normalizeComponents(child);
            }
        }
    }

    private static void normalizeFont(Component component) {
        Font font = component.getFont();
        if (font == null || font.getSize() <= 18) {
            return;
        }

        int size = font.getSize() >= 30 ? 22 : 18;
        component.setFont(font.deriveFont((float) size));
    }

    private static void normalizeIcon(Component component) {
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            label.setIcon(scaleIcon(label.getIcon(), 680, 430));
        } else if (component instanceof AbstractButton) {
            AbstractButton button = (AbstractButton) component;
            button.setIcon(scaleIcon(button.getIcon(), 270, 64));
        }
    }

    private static javax.swing.Icon scaleIcon(javax.swing.Icon icon, int maxWidth, int maxHeight) {
        if (!(icon instanceof ImageIcon)) {
            return icon;
        }

        ImageIcon imageIcon = (ImageIcon) icon;
        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();

        if (width <= 0 || height <= 0) {
            return icon;
        }

        int targetWidth = maxWidth;
        int targetHeight = maxHeight;
        if (width <= 220 && height <= 220) {
            targetWidth = 130;
            targetHeight = 130;
        }

        double scale = Math.min(1.0, Math.min((double) targetWidth / width, (double) targetHeight / height));
        if (scale >= 1.0) {
            return icon;
        }

        int scaledWidth = Math.max(1, (int) Math.round(width * scale));
        int scaledHeight = Math.max(1, (int) Math.round(height * scale));
        Image image = imageIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    private static void fitToScreen(JFrame frame) {
        Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        frame.setMinimumSize(new Dimension(Math.min(900, bounds.width), Math.min(580, bounds.height)));
        frame.setSize(bounds.width, bounds.height);
    }
}
