package utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;

public class Layout
{
  public static void addComp(Component c, Container container, GridBagConstraints gbConstraints, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty)
  {
    gbConstraints.gridx = gridx;
    gbConstraints.gridy = gridy;
    gbConstraints.gridwidth = gridwidth;
    gbConstraints.gridheight = gridheight;
    gbConstraints.weightx = weightx;
    gbConstraints.weighty = weighty;
    container.add(c, gbConstraints);
  }
}