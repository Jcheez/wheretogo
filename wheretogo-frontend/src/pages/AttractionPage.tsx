import { Grid } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { deleteAttraction, getAllAttractions } from '../apis/attractions'
import AttractionCard from '../components/AttractionCard'
import LoadingIcon from '../components/LoadingIcon'
import { attraction } from '../interfaces'

export default function Attraction() {

    const [attractions, setAttractions] = useState<attraction[]>([])
    const [isLoading, setIsLoading] = useState<boolean>(true)

    useEffect(() => {
        getAllAttractions().then(attr => {
            console.log(attr)
            setAttractions(attr)
            setIsLoading(false)
        })
    }, [])

    const removeAttraction = (id: string) => {
        deleteAttraction(id).then(res => {
            if (res !== 204) {
                alert("This was not deleted")
            }
        })
        setAttractions(attractions.filter(val => val.id.localeCompare(id) !== 0))
    }

    const renderAttractions = (attractions: attraction[]) => {
        const isMock = false
        const res: React.ReactElement[] = []
        for (let attraction of attractions) {
            res.push(
                <Grid item xs={6} md={2} key={attraction.id}>
                    <AttractionCard isMock={isMock} payload={attraction} id={attraction.id} removeFunc={() => removeAttraction(attraction.id)}></AttractionCard>
                </Grid>
            )
        }
        return res
    }

    return (
        <div>
            {isLoading ? <LoadingIcon></LoadingIcon> :
                <div>
                    <br />
                    <Grid container spacing={3}>
                        {renderAttractions(attractions)}
                    </Grid>
                </div>}
        </div>
    )
}
