import { CircularProgress } from '@mui/material'
import React from 'react'

export default function LoadingIcon() {
  
    const styling = {
        display: "flex", 
        alignItems: "center", 
        justifyContent: "center",
        height: "90vh"
    }
  
    return (
    <div style={styling}>
        <CircularProgress size={100}/>
    </div>
  )
}
