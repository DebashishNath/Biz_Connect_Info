import React, { useEffect, useState } from 'react';
import { Outlet, useNavigate } from 'react-router-dom';
import { AppBar,Toolbar,IconButton,Typography,MenuItem,MenuList,Paper,Popover,ListItemIcon,Box } from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import CategoryIcon from '@mui/icons-material/Category';

import LogoutIcon from '@mui/icons-material/Logout';
import Inventory2Icon from '@mui/icons-material/Inventory2';

import './mainmenu.css';
import { useAuth, getFormattedDate, getFormattedTime } from '@kcndigitals/lib';

const MainMenuForm: React.FC = () => {
  const navigate = useNavigate();
  const auth = useAuth();

  const [time, setTime] = useState(getFormattedTime());
  const date = getFormattedDate();

  const [menuAnchor, setMenuAnchor] = useState<HTMLElement | null>(null);
  const [submenuAnchor, setSubmenuAnchor] = useState<HTMLElement | null>(null);
  const [submenuItems, setSubmenuItems] = useState<
    { label: string; path?: string; icon: React.ReactNode }[]>([]);


  useEffect(() => {
    const timer = setInterval(() => setTime(getFormattedTime()), 1000);
    return () => clearInterval(timer);
  }, []);

  const handleMenuOpen = (e: React.MouseEvent<HTMLElement>) => {
    setMenuAnchor(e.currentTarget);
  };

  const handleMenuClose = () => {
    setMenuAnchor(null);
    setSubmenuAnchor(null);
  };

  const handleSubmenuOpen = (
    e: React.MouseEvent<HTMLElement>,
    items: { label: string; path?: string; icon: React.ReactNode }[]
  ) => {
    setSubmenuAnchor(e.currentTarget);
    setSubmenuItems(items);
  };

  const handleNavigation = (path?: string) => {
    if (path) navigate(`/mainmenu/${path}`);
    handleMenuClose();
  };

  const handleLogout = () => {
    auth.logout();
    handleMenuClose();
  };

  return (
    <>
      {/* Background */}
      <div className="mainmenu-background-image" />

      <Box sx={{ position: 'relative', zIndex: 1 }}>
        {/* App Bar */}
        <AppBar position="sticky">
          <Toolbar>
            <IconButton color="inherit" edge="start" onClick={handleMenuOpen}>
              <MenuIcon />
            </IconButton>

            <Typography variant="h6" sx={{ flexGrow: 1 }}>
              Business Connect Pro
            </Typography>

            <Typography variant="h6">
              Welcome, {auth.fullname}
            </Typography>

            <Box sx={{ ml: 4 }}>
              <Typography variant="h6">
                {date} &nbsp; {time}
              </Typography>
            </Box>
          </Toolbar>
        </AppBar>

        {/* Main Menu */}
        <Popover
          open={Boolean(menuAnchor)}
          anchorEl={menuAnchor}
          onClose={handleMenuClose}
          anchorOrigin={{ vertical: 'bottom', horizontal: 'left' }}
        >
          <Paper>
            <MenuList>
              <MenuItem
                onClick={(e) =>
                  handleSubmenuOpen(e, [
                    { label: 'Country', path: 'country', icon: <Inventory2Icon fontSize="small" /> },
                    { label: 'State', path: 'state', icon: <Inventory2Icon fontSize="small" /> },
                    { label: 'City', path: 'city', icon: <Inventory2Icon fontSize="small" /> },
                    { label: 'Client', path: 'client', icon: <Inventory2Icon fontSize="small" /> },
                  ])
                }
              >
                <ListItemIcon><CategoryIcon /></ListItemIcon>
                Master
              </MenuItem>

              <MenuItem
                onClick={(e) =>
                  handleSubmenuOpen(e, [
                    { label: 'Lead', path: 'lead', icon: <Inventory2Icon fontSize="small" /> },
                  ])
                }
              >
                <ListItemIcon><CategoryIcon /></ListItemIcon>
                Transaction
              </MenuItem>
              
              {/* <MenuItem onClick={(e) =>
                handleSubmenuOpen(e, [
                  { label: 'Customer Analyzer Dashboard', path: 'customer-analyzer-dashboard', icon: <SellIcon fontSize="small" /> },
                  { label: 'Customer Sales Report', path: 'customer_sale_report', icon: <SellIcon fontSize="small" /> },
                  { label: 'Detailed Sales Report', path: 'sale_report', icon: <SellIcon fontSize="small" /> },
                  { label: 'Sales Trend Report', path: 'sales_trend_report', icon: <SellIcon fontSize="small" /> },
                  { label: 'Product Sales Report', path: 'product_sale_report', icon: <SellIcon fontSize="small" /> },
                  { label: 'Defaulter List', path: 'defaulter_list', icon: <SellIcon fontSize="small" /> }
                ])
              }>
                <ListItemIcon><CompareArrowsIcon fontSize="small" /></ListItemIcon>
                Report
              </MenuItem> */}
              
              <MenuItem onClick={handleLogout}>
                <ListItemIcon><LogoutIcon /></ListItemIcon>
                Logout
              </MenuItem>
            </MenuList>
          </Paper>
        </Popover>

        {/* Submenu */}
        <Popover
          open={Boolean(submenuAnchor)}
          anchorEl={submenuAnchor}
          onClose={() => setSubmenuAnchor(null)}
          anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
        >
          <Paper>
            <MenuList>
              {submenuItems.map((item, i) => (
                <MenuItem key={i} onClick={() => handleNavigation(item.path)}>
                  <ListItemIcon>{item.icon}</ListItemIcon>
                  {item.label}
                </MenuItem>
              ))}
            </MenuList>
          </Paper>
        </Popover>

        {/* Page Content */}
        <Box sx={{ mt: 3, px: 2 }}>
          <Outlet />
        </Box>

        {/* 🤖 Floating Chat Button */}
        {/* <Fab
          color="primary"
          sx={{ position: 'fixed', bottom: 24, right: 24 }}
          onClick={() => setChatOpen(true)}
        >
          <ChatIcon />
        </Fab>

        🤖 Chatbot Drawer
        <Drawer
          anchor="right"
          open={chatOpen}
          onClose={() => setChatOpen(false)}
        >
          <ChatBotScreen onClose={() => setChatOpen(false)} />
        </Drawer> */}
      </Box>
    </>
  );
};

export default MainMenuForm;