import { Grid } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { getMockAttractions } from '../apis/mockAttractions'
import AttractionCard from '../components/AttractionCard'
import { attraction } from '../interfaces'
import { deleteMockAttraction } from '../apis/mockAttractions'
import LoadingIcon from '../components/LoadingIcon'

export default function MockAttractionPage() {

    const [mockAttractions, setMockAttractions] = useState<attraction[]>([])
    const [isLoading, setIsLoading] = useState<boolean>(true)

    useEffect(() => {
        getMockAttractions().then(mocks => {
            setMockAttractions(mocks)
            setIsLoading(false)
        })
    }, [])

    const removeMockAttraction = (id: string) => {
        deleteMockAttraction(id).then(res => {
            if (res !== 204) {
                alert("This was not deleted")
            }
        })
        setMockAttractions(mockAttractions.filter(val => val.id.localeCompare(id) !== 0))
    }

    const renderMockAttractions = (mockAttractions: attraction[]) => {
        const isMock: boolean = true
        const res: React.ReactElement[] = []
        for (let mockAttraction of mockAttractions) {
            res.push(
                <Grid item xs={6} md={2} key={mockAttraction.id}>
                    <AttractionCard isMock={isMock} payload={mockAttraction} id={mockAttraction.id} removeFunc={() => removeMockAttraction(mockAttraction.id)}></AttractionCard>
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
                        {renderMockAttractions(mockAttractions)}
                    </Grid>
                </div>}
        </div>
    )
}
