/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_products_manager_;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter; 
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author MSI
 */
public class ManageProducts_Frame extends javax.swing.JFrame {

    /**
     * Creates new form ManageProducts_Frame
     */
    
    //downlaod the mysql connector
    
    //create border 
    Border panel_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.darkGray);
    Border textField_border = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.darkGray);
    // arraylist of products
    ArrayList<Product> productsArray = new ArrayList<>(); 
    
    // int variable for navigation
    int position  = 0; 
    
    public ManageProducts_Frame() {
        initComponents();
        
        //center the form 
        this.setLocationRelativeTo(null);
        // show the close icon 
        displayImage("\\/Images/x.png", jLabel_close, 'r'); 
        // set border
        jPanel_Container.setBorder(panel_border);
        jTextField_name.setBorder(textField_border);
        jTextField_Quantity.setBorder(textField_border);
        jTextField_price.setBorder(textField_border);
        jTextField_imgPath.setBorder(textField_border); 
        
        // change the row height 
        jTable_products_.setRowHeight(35);
        
        // populate the jtable with products 
        showProductsInTable();
            
    }
    // populate the jtable with products we first need an arraylist of all products 
    // create a function that return an arraylist of products 
    public ArrayList<Product> getProductsList()
    {
        ArrayList<Product> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM `products`";
        
        Statement st; 
        ResultSet rs;
        
        try {
            st = DB.getConnection().createStatement();
                    rs = st.executeQuery(selectQuery);
            Product product;
            while(rs.next())
            {
                product = new Product(rs.getInt("id"), rs.getString("name"), 
                                      rs.getString("category"), rs.getInt("quantity"),
                                      rs.getDouble("price"), rs.getString("image_path"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageProducts_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        productsArray = list; 
        return list;
    }
    
    // create a function to show products in table
    public void showProductsInTable()
    {
        ArrayList<Product> productsList = getProductsList();
        DefaultTableModel model = (DefaultTableModel) jTable_products_.getModel();
        Object[] row = new Object[6]; // 6 the number of colomns
        
        // clear jtable
        model.setRowCount(0);
        
        for(int i = 0; i < getProductsList().size(); i++)
        {
            row[0] = productsList.get(i).getId();
            row[1] = productsList.get(i).getName();
            row[2] = productsList.get(i).getCategory();
            row[3] = productsList.get(i).getQuantity();
            row[4] = productsList.get(i).getPrice();
            row[5] = productsList.get(i).getImage_path();
            
            model.addRow(row);
        }
    }
    
    //create a function to display product data by index
    public void showProductData(int index)
    {
        jSpinner_id.setValue(productsArray.get(index).getId());
        jTextField_name.setText(productsArray.get(index).getName());
        jComboBox_Category.setSelectedItem(productsArray.get(index).getCategory());
        jTextField_Quantity.setText(productsArray.get(index).getQuantity().toString());
        jTextField_price.setText(productsArray.get(index).getPrice().toString());
        jTextField_imgPath.setText(productsArray.get(index).getImage_path());
        displayImage(productsArray.get(index).getImage_path(), jLabel_image, 'a');
    }
    
    // create a function to check empty fields 
    public boolean checkEmptyFields()
    {
        String name = jTextField_name.getText().trim();
        String quantity = jTextField_Quantity.getText().trim();
        String price = jTextField_price.getText().trim(); 
        String imagePath = jTextField_imgPath.getText().trim(); 
        
        if(name.equals("") || quantity.equals("") || price.equals("") || imagePath.equals(""))
        {
            return false; // if one or more fields are empty return false else return true
        }
        else
        {
            return true;
        }
    }
    
    // create a function to display image into jlabel
    public void displayImage(String imgPath, JLabel label, char rsc)
    {
        ImageIcon imgIco;
        // check if the image is from the project files
        // if the image is from the resource rsc = 'r'
        if(rsc == 'r')
        {
            imgIco = new ImageIcon(getClass().getResource(imgPath));
        }
        else 
        {
        imgIco = new ImageIcon(imgPath);
        }
        Image img = imgIco.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(img));
    }
    
    // create a function to clear fields
    public void clearFields()
    {
        jSpinner_id.setValue(0);
        jTextField_name.setText("");
        jTextField_Quantity.setText("");
        jComboBox_Category.setSelectedIndex(0);
        jTextField_price.setText("");
        jTextField_imgPath.setText("");
        jLabel_image.setIcon(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Container = new javax.swing.JPanel();
        jLabel_close = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_products_ = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jSpinner_id = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_Quantity = new javax.swing.JTextField();
        jTextField_name = new javax.swing.JTextField();
        jTextField_price = new javax.swing.JTextField();
        jComboBox_Category = new javax.swing.JComboBox<>();
        jLabel_image = new javax.swing.JLabel();
        jButton_add_ = new javax.swing.JButton();
        jButton_edit_ = new javax.swing.JButton();
        jButton_remove_ = new javax.swing.JButton();
        jButton_search_ = new javax.swing.JButton();
        jButton_browse_ = new javax.swing.JButton();
        jTextField_imgPath = new javax.swing.JTextField();
        jButton_first_ = new javax.swing.JButton();
        jButton_previous_ = new javax.swing.JButton();
        jButton_last_ = new javax.swing.JButton();
        jButton_next_ = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel_Container.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_close.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel_close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseClicked(evt);
            }
        });

        jTable_products_.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Category", "Quantity", "Price", "Image"
            }
        ));
        jTable_products_.setSelectionBackground(new java.awt.Color(0, 153, 51));
        jTable_products_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_products_MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_products_);

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Name:");

        jSpinner_id.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("ID:");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Category:");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Quantity:");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Price:");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Image:");

        jTextField_Quantity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_Quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_QuantityActionPerformed(evt);
            }
        });
        jTextField_Quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_QuantityKeyTyped(evt);
            }
        });

        jTextField_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nameActionPerformed(evt);
            }
        });

        jTextField_price.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_priceActionPerformed(evt);
            }
        });
        jTextField_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_priceKeyReleased(evt);
            }
        });

        jComboBox_Category.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jComboBox_Category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cars", "Electronics", "Phone", "Books" }));

        jLabel_image.setBackground(new java.awt.Color(102, 102, 102));
        jLabel_image.setOpaque(true);

        jButton_add_.setBackground(new java.awt.Color(0, 0, 0));
        jButton_add_.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_add_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_add_.setText("Add");
        jButton_add_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_add_ActionPerformed(evt);
            }
        });

        jButton_edit_.setBackground(new java.awt.Color(0, 0, 0));
        jButton_edit_.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_edit_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_edit_.setText("Edit");
        jButton_edit_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_edit_ActionPerformed(evt);
            }
        });

        jButton_remove_.setBackground(new java.awt.Color(0, 0, 0));
        jButton_remove_.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_remove_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_remove_.setText("Remove");
        jButton_remove_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_remove_ActionPerformed(evt);
            }
        });

        jButton_search_.setBackground(new java.awt.Color(255, 102, 0));
        jButton_search_.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_search_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_search_.setText("Search");
        jButton_search_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_search_ActionPerformed(evt);
            }
        });

        jButton_browse_.setBackground(new java.awt.Color(255, 102, 0));
        jButton_browse_.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_browse_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_browse_.setText("Browse");
        jButton_browse_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_browse_ActionPerformed(evt);
            }
        });

        jTextField_imgPath.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTextField_imgPath.setToolTipText("");
        jTextField_imgPath.setEnabled(false);
        jTextField_imgPath.setMaximumSize(new java.awt.Dimension(6, 19));

        jButton_first_.setBackground(new java.awt.Color(102, 0, 0));
        jButton_first_.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_first_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_first_.setText("<<");
        jButton_first_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_first_ActionPerformed(evt);
            }
        });

        jButton_previous_.setBackground(new java.awt.Color(102, 0, 0));
        jButton_previous_.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_previous_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_previous_.setText("<");
        jButton_previous_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_previous_ActionPerformed(evt);
            }
        });

        jButton_last_.setBackground(new java.awt.Color(102, 0, 0));
        jButton_last_.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_last_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_last_.setText(">>");
        jButton_last_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_last_ActionPerformed(evt);
            }
        });

        jButton_next_.setBackground(new java.awt.Color(102, 0, 0));
        jButton_next_.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_next_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_next_.setText(">");
        jButton_next_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_next_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ContainerLayout = new javax.swing.GroupLayout(jPanel_Container);
        jPanel_Container.setLayout(jPanel_ContainerLayout);
        jPanel_ContainerLayout.setHorizontalGroup(
            jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_Category, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                                        .addComponent(jTextField_imgPath, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton_browse_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField_price, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinner_id, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_search_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_image, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_ContainerLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jButton_add_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_edit_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_remove_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_first_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_next_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jButton_previous_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_last_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
            .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_close, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_ContainerLayout.setVerticalGroup(
            jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_close, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jSpinner_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_search_, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox_Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField_Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_imgPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_browse_, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_image, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton_first_, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_previous_, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_last_, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_next_, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton_add_, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_edit_, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_remove_, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 54, Short.MAX_VALUE))
                    .addGroup(jPanel_ContainerLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked
        // Tclose form 
        this.dispose();
    }//GEN-LAST:event_jLabel_closeMouseClicked

    private void jTextField_QuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_QuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_QuantityActionPerformed

    private void jTextField_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nameActionPerformed

    private void jTextField_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_priceActionPerformed

    private void jButton_edit_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_edit_ActionPerformed
        // edit the selected product
        
        if(checkEmptyFields())
        {
        Integer id = Integer.valueOf(jSpinner_id.getValue().toString());
        String name = jTextField_name.getText(); 
        String category = jComboBox_Category.getSelectedItem().toString(); 
        Integer quantity = Integer.valueOf(jTextField_Quantity.getText()); 
        Double price = Double.valueOf(jTextField_price.getText()); 
        String img = jTextField_imgPath.getText();
        
        String updateQuery = "UPDATE `products` SET `name`=?,`category`=?,`quantity`=?,`price`=?,`image_path`=? WHERE `id` = ?";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(updateQuery);
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.setString(5, img);
            ps.setInt(6, id);
            
            if(ps.executeUpdate() > 0)
            {
                showProductsInTable();
                JOptionPane.showMessageDialog(null, "Product Updated", "Edit Product", JOptionPane.INFORMATION_MESSAGE);
               // System.out.println("Product Updated");
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Product Not Updated", "Edit Product", JOptionPane.ERROR_MESSAGE);
                //System.out.println("Some Error Message Here");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageProducts_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Emty", "Edit Product", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("One Or More Fields Are Emty");
        }    
    }//GEN-LAST:event_jButton_edit_ActionPerformed

    private void jButton_remove_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_remove_ActionPerformed
        // remove the selected product
        if(Integer.valueOf(jSpinner_id.getValue().toString()) > 0)
        {
        Integer id = Integer.valueOf(jSpinner_id.getValue().toString());
        
        String deleteQuery = "DELETE FROM `products` WHERE `id` = ?";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(deleteQuery);
            ps.setInt(1, id);
            
            // shpw a confirmation box before deleting the product 
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?", "Remove Product", JOptionPane.YES_NO_OPTION);
            
            if(confirm == JOptionPane.YES_OPTION)
            {
            if(ps.executeUpdate() > 0)
            {
                showProductsInTable();
                JOptionPane.showMessageDialog(null, "Product Deleted Successfully", "Remove Product", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Product Deleted");
                clearFields();
            } 
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Product Not Deleted, Make Sure The ID is Valid", "Remove Product", JOptionPane.ERROR_MESSAGE);
                //System.out.println("Some Error Message Here");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageProducts_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
    }//GEN-LAST:event_jButton_remove_ActionPerformed

    private void jButton_add_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_add_ActionPerformed
        // add a new produt 
        //INSERT INTO `products`(`name`, `category`, `quantity`, `price`, `image_path`) VALUES (?,?,?,?,?)
        if(checkEmptyFields())
        {
        String name = jTextField_name.getText(); 
        String category = jComboBox_Category.getSelectedItem().toString(); 
        Integer quantity = Integer.valueOf(jTextField_Quantity.getText()); 
        Double price = Double.valueOf(jTextField_price.getText()); 
        String img = jTextField_imgPath.getText();
        
        String insertQuery = "INSERT INTO `products`(`name`, `category`, `quantity`, `price`, `image_path`) VALUES (?,?,?,?,?)";
        
        try {
            PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.setString(5, img);
            
            if(ps.executeUpdate() > 0)
            {
                showProductsInTable();
                JOptionPane.showMessageDialog(null, "New Product Added Successfully", "Add Product", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("New Product Added");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Product Not Add", "Add Product", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Some Error Message Here");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageProducts_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else       
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Emty", "Add Product", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("One or More Fields Are Emty");
        }  
    }//GEN-LAST:event_jButton_add_ActionPerformed

    private void jButton_search_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_search_ActionPerformed
        // search product by id
        
        if(checkEmptyFields())
        {
        int id = Integer.valueOf(jSpinner_id.getValue().toString()); 
        String selectQuery = "SELECT * FROM `products` WHERE `id` = ?";
       
        try {
            Statement st = DB.getConnection().createStatement();
            ResultSet rs = st.executeQuery(selectQuery);
            
            if(rs.next())
            {
                jTextField_name.setText(rs.getString("name"));
                jComboBox_Category.setSelectedItem(rs.getString("category"));
                jTextField_Quantity.setText(rs.getString("quantity"));
                jTextField_price.setText(String.valueOf(rs.getDouble("price")));
                jTextField_imgPath.setText(rs.getString("image_path")); 
                displayImage(rs.getString("image_path"), jLabel_image, 'a'); 
                
            }
            else
            {
                System.out.println("No Product With This ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageProducts_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else       
        {
            System.out.println("One or More Fields Are Emty");
        }     
    }//GEN-LAST:event_jButton_search_ActionPerformed

    private void jButton_browse_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_browse_ActionPerformed
        // browse and display image in jlabel
        JFileChooser filechooser = new JFileChooser(); 
        filechooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*images", ".png", "jpg", ".jpeg");
        filechooser.addChoosableFileFilter(filter);
        
        if(filechooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
        {
          File selectedImage = filechooser.getSelectedFile();
          String image_path = selectedImage.getAbsolutePath();
          displayImage(image_path, jLabel_image, 'a');
          jTextField_imgPath.setText(image_path);
          System.out.println(image_path);
        } 
        else
        {
            System.out.println("no file selected");
        }
    }//GEN-LAST:event_jButton_browse_ActionPerformed

    private void jTable_products_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_products_MouseClicked
        // display the selected products info 
        int index = jTable_products_.getSelectedRow();
        showProductData(index);
        position = index; 
    }//GEN-LAST:event_jTable_products_MouseClicked

    private void jButton_first_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_first_ActionPerformed
        // show the first product 
        position = 0;
        showProductData(position);
        jTable_products_.setRowSelectionInterval(position, position);
    }//GEN-LAST:event_jButton_first_ActionPerformed

    private void jButton_previous_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_previous_ActionPerformed
        // show the previous product 
        position--;
        if(position < 0)
        {
            position = 0;
        }
        showProductData(position);
        jTable_products_.setRowSelectionInterval(position, position);
    }//GEN-LAST:event_jButton_previous_ActionPerformed

    private void jButton_last_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_last_ActionPerformed
        // show the last product 
        position = productsArray.size()-1;
        showProductData(position);
        jTable_products_.setRowSelectionInterval(position, position);
      
    }//GEN-LAST:event_jButton_last_ActionPerformed

    private void jButton_next_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_next_ActionPerformed
        // show the next product 
        position++;
        if(position > productsArray.size() - 1)
        {
            position = productsArray.size() - 1;
        }
        showProductData(position);
        jTable_products_.setRowSelectionInterval(position, position);
    }//GEN-LAST:event_jButton_next_ActionPerformed

    private void jTextField_QuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_QuantityKeyTyped
        // allow only numbers 
        // or you can user jspinner
        if(!Character.isDigit(evt.getKeyChar()))
        {
            evt.consume(); 
        }
    }//GEN-LAST:event_jTextField_QuantityKeyTyped

    private void jTextField_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_priceKeyReleased
        // allow double 
        try 
        {
            Double.valueOf(jTextField_price.getText());
        }
        catch(NumberFormatException ex)
        {
            System.out.println(ex.getMessage());
            jTextField_price.setText("");
        }
    }//GEN-LAST:event_jTextField_priceKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageProducts_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageProducts_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageProducts_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageProducts_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageProducts_Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_add_;
    private javax.swing.JButton jButton_browse_;
    private javax.swing.JButton jButton_edit_;
    private javax.swing.JButton jButton_first_;
    private javax.swing.JButton jButton_last_;
    private javax.swing.JButton jButton_next_;
    private javax.swing.JButton jButton_previous_;
    private javax.swing.JButton jButton_remove_;
    private javax.swing.JButton jButton_search_;
    private javax.swing.JComboBox<String> jComboBox_Category;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_image;
    private javax.swing.JPanel jPanel_Container;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner_id;
    private javax.swing.JTable jTable_products_;
    private javax.swing.JTextField jTextField_Quantity;
    private javax.swing.JTextField jTextField_imgPath;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JTextField jTextField_price;
    // End of variables declaration//GEN-END:variables
}
