import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, MenuItem, TextField } from '@mui/material'
import AddLocationAltOutlinedIcon from '@mui/icons-material/AddLocationAltOutlined';
import HighlightOffOutlinedIcon from '@mui/icons-material/HighlightOffOutlined';
import { Box } from '@mui/system'
import React, { useState } from 'react'
import { dialogPayload, location } from '../interfaces'
import { postAttraction } from '../apis/attractions';
import { UploadFileOutlined } from '@mui/icons-material';
import { postImage } from '../apis/image';

export default function AttractionFormDialog(props: dialogPayload) {

    const attractionTypes = [
        {
            value: "RESTAURANT",
            label: "Restaurant"
        },
        {
            value: "CAFE",
            label: "Cafe",
        },
        {
            value: "DESSERT",
            label: "Dessert"
        },
        {
            value: "EXERCISE",
            label: "Exercise"
        },
        {
            value: "SIGHTS",
            label: "Sights"
        },
        {
            value: "OTHERS",
            label: "Others"
        }
    ]

    const [name, setName] = useState<string>(props.attraction.name)
    const [readMore, setReadMore] = useState<string>("")
    const [cost, setCost] = useState<number>(props.attraction.cost)
    const [type, setType] = useState<string>(props.attraction.type)
    const [desc, setDesc] = useState<string>("")
    const [locations, setLocations] = useState<location[]>([{ id: 0 }])
    const [formData, setFormData] = useState<FormData>(new FormData())

    const handleAttractionChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setType(event.target.value)
    }

    const handleNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setName(event.target.value)
    }

    const handleReadMoreChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setReadMore(event.target.value)
    }

    const handleCostChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setCost(parseInt(event.target.value))
    }

    const handleDescChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setDesc(event.target.value)
    }

    const handleAddressChange = (index: number) => {
        return (event: React.ChangeEvent<HTMLInputElement>) => {
            locations[index].address = event.target.value
        }
    }

    const handleMRTChange = (index: number) => {
        return (event: React.ChangeEvent<HTMLInputElement>) => {
            locations[index].nearestMRT = event.target.value
        }
    }

    const handleLatChange = (index: number) => {
        return (event: React.ChangeEvent<HTMLInputElement>) => {
            locations[index].latitude = parseFloat(event.target.value)
        }
    }

    const handleLongChange = (index: number) => {
        return (event: React.ChangeEvent<HTMLInputElement>) => {
            locations[index].longitude = parseFloat(event.target.value)
        }
    }

    const handleImageUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (!e.target.files) {
            return;
        }
        const file = e.target.files[0];
        const formData = new FormData()
        formData.append("title", file.name)
        formData.append("image", file)
        setFormData(formData)
    };

    const handleOnSubmit = () => {

        postImage(formData).then(id => {
            const payload = {
                name: name,
                locations: locations,
                photoId: id,
                description: desc,
                readMoreLink: readMore,
                cost: cost,
                type: type
            }
            postAttraction(payload)
            props.onClose()
            props.remove()
        })
    }

    const addSubLocation = () => {
        const newLocationArr = [...locations]
        newLocationArr.push({
            id: newLocationArr.length
        })
        setLocations(newLocationArr)
    }

    const deleteSubLocation = (index: number) => {
        const newLocationArr = [...locations]
        setLocations(newLocationArr.filter(location => location.id !== index))
    }

    const renderSubLocations = (locations: location[]) => {
        const res: React.ReactElement[] = []
        for (let location of locations.slice(1)) {
            res.push(
                <Box sx={{
                    '& .MuiTextField-root': { m: 1, width: '25ch' },
                }} key={location.id}>
                    <TextField id="address" label="Address" variant='standard' defaultValue={location.address} onChange={handleAddressChange(location.id)} />
                    <TextField id="mrt" label="Nearest MRT" variant='standard' defaultValue={location.nearestMRT} onChange={handleMRTChange(location.id)} />
                    <TextField id="lat" label="Latitude" variant='standard' defaultValue={location.latitude} onChange={handleLatChange(location.id)} />
                    <TextField id="long" label="Longitude" variant='standard' defaultValue={location.longitude} onChange={handleLongChange(location.id)} />
                    <Button color='error' endIcon={<HighlightOffOutlinedIcon />} size="large" onClick={() => deleteSubLocation(location.id)}></Button>
                </Box>
            )
        }
        return res
    }

    return (
        <Dialog open={props.open} onClose={() => props.onClose()}>
            <DialogTitle>Attraction Validation</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Fill up this form to create a full fledged Attraction to visit
                </DialogContentText>
                <Box sx={{
                    '& .MuiTextField-root': { m: 1, width: '25ch' },
                }}>
                    <TextField id="name" label="Attraction Name" variant='standard' defaultValue={name} onChange={handleNameChange} />
                    <TextField type={"url"} id="readmore" label="Read More" variant='standard' defaultValue={readMore} onChange={handleReadMoreChange} />
                    <TextField type="number" id="cost" label="Estimated Cost" variant='standard' defaultValue={cost} onChange={handleCostChange} />
                    <TextField id="type" select label="Attraction Type" value={type} onChange={handleAttractionChange}>
                        {attractionTypes.map((option) => (
                            <MenuItem key={option.value} value={option.value}>
                                {option.label}
                            </MenuItem>
                        ))}
                    </TextField>
                </Box>
                <TextField multiline fullWidth id="desc" label="Description" sx={{ m: 1, width: '52ch' }} onChange={handleDescChange} defaultValue={desc} />
                <br />
                <Box sx={{
                    '& .MuiTextField-root': { m: 1, width: '25ch' },
                }}>
                    <TextField id="address" label="Address" variant='standard' onChange={handleAddressChange(0)} defaultValue={locations[0].address} />
                    <TextField id="mrt" label="Nearest MRT" variant='standard' onChange={handleMRTChange(0)} defaultValue={locations[0].nearestMRT} />
                    <TextField id="lat" label="Latitude" variant='standard' onChange={handleLatChange(0)} defaultValue={locations[0].latitude} />
                    <TextField id="long" label="Longitude" variant='standard' onChange={handleLongChange(0)} defaultValue={locations[0].longitude} />
                </Box>
                {renderSubLocations(locations)}
                <Button endIcon={<AddLocationAltOutlinedIcon />} onClick={addSubLocation}>Add Location</Button>
                <br />
                <Button
                    component="label"
                    variant="outlined"
                    startIcon={<UploadFileOutlined />}
                    sx={{ marginRight: "1rem" }}
                >
                    Upload
                    <input type="file" accept="image/*" hidden onChange={handleImageUpload} />
                </Button>
            </DialogContent >
            <DialogActions>
                <Button onClick={() => props.onClose()}>Cancel</Button>
                <Button onClick={handleOnSubmit}>Submit</Button>
            </DialogActions>
        </Dialog >
    )
}
