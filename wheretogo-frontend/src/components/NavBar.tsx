import { AppBar, Button, Toolbar, Typography } from '@mui/material'
import { Box } from '@mui/system'
import React from 'react'

export default function NavBar() {
    
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <Typography
                        component="a"
                        href="/"
                        sx={{
                            mr: 2,
                            fontWeight: 700,
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        WhereToGo
                    </Typography>
                    <Box>
                        <Button sx={{ color: 'white', fontWeight: 700 }} size="large" href="/attractions">Attractions</Button>
                        <Button sx={{ color: 'white', fontWeight: 700 }} size="large" href="/mockattractions">Mocks</Button>
                    </Box>
                </Toolbar>
            </AppBar>
        </Box>
    )
}
